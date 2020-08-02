<template>
    <div id="CSVParser">
        <div class="panel-body">
            <div class="form-group">
                <label for="csvFile" class="control-label col-sm-3 text-right">Import CSV file</label>
                <div class="col-sm-9">
                    <input type="file" id="csvFile" ref="csvFile" name="csvFile" class="form-control"
                           @change="parseCSV($event)">
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {bus, serverAPIPath} from "../main";

    export default {
        name: "CSVParser",
        methods: {
            csvToJSON(csv) {
                let lines = csv.trim().split("\n");
                //Remove header line
                lines.shift();
                let result = [];

                lines.forEach(line => {
                    let obj = {};
                    let currentLine = line.split(",");
                    obj["bankName"] = currentLine[0];
                    // Only accept digits - a much more comprehensive validation would be to rollback
                    // the REST request if one of the fields in the CSV file isn't valid.
                    obj["cardNumber"] = currentLine[1].replace(/[^0-9.]+/g, '');
                    obj["expiryDate"] = new Date(currentLine[2]).getTime();
                    result.push(obj)
                });

                fetch(serverAPIPath + "addCustomerBanksMultiple", {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(result)
                }).then(resp => resp.json())
                    .then(data => {
                        bus.$emit('updateBankTable', data);
                        console.log('Success: ', data);
                    })
                    .catch((error) => {
                        console.error('Error: ', error);
                    });
                this.$refs.csvFile.value = null;
            },
            parseCSV(e) {
                let v = this;
                if (window.FileReader) {
                    let reader = new FileReader();
                    reader.readAsText(e.target.files[0]);

                    reader.onload = function (event) {
                        let csv = event.target.result;
                        v.parse_csv = v.csvToJSON(csv)

                    };
                    reader.onerror = function (evt) {
                        if (evt.target.error.name === "NotReadableError") {
                            alert("Non Readable CSV file");
                        }
                    };
                } else {
                    alert('FileReader not supported by this browser.');
                }

            }
        }
    }
</script>

<style scoped>

</style>