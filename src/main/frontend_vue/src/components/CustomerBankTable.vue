<template>
    <div id="CustomerBankTable">
        <vue-table-dynamic :params="params"/>
    </div>
</template>

<script>
    import VueTableDynamic from 'vue-table-dynamic'
    import {bus, serverAPIPath} from "../main";

    export default {
        name: "CustomerBankTable",
        components: {
            VueTableDynamic
        },
        data() {
            return {
                params: {
                    data: []
                }
            }
        },
        created() {
            bus.$on('updateBankTable', (data) => {
                this.updateTable(data);
            });
            fetch(serverAPIPath + "getCustomerBanks")
                .then(resp => {
                    return resp.json()
                }).then(data => {
                this.updateTable(data);
            })
        },
        methods: {
            updateTable(data) {
                let returnArray = [];
                returnArray.push(['Bank', 'Card number', 'Expiry date']);

                data.forEach(customerBank => {
                    let arr = [];
                    let expDate = new Date(customerBank.expiryDate);
                    arr.push(customerBank.bankName, customerBank.obfuscatedCardNumber,
                        expDate.toLocaleString('default', {month: 'short'}) + "/" + expDate.getFullYear());
                    returnArray.push(arr);
                });

                this.params.data = returnArray;
            }
        }
    }
</script>

<style scoped>
    .v-table-dynamic {
        font-size: 15px;
    }
</style>