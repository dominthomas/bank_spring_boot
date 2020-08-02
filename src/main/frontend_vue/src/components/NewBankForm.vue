<template>
    <form id="newBankForm" v-on:submit.prevent="submit">
        <div class="row">
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Bank name<span class="text-danger">*</span></label>
                <input type="text" v-model.trim="$v.bankName.$model"
                       :class="{'is-invalid': validationStatus($v.bankName)}" class="form-control form-control-lg">
                <div v-if="!$v.bankName.required" class="invalid-feedback">Please provide a bank name.</div>
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Card number<span class="text-danger">*</span></label>
                <input type="number" v-model.trim="$v.cardNumber.$model"
                       :class="{'is-invalid': validationStatus($v.cardNumber)}" class="form-control form-control-lg">
                <div v-if="!$v.cardNumber.required" class="invalid-feedback">Please provide a card number.</div>
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Expiry date<span class="text-danger">*</span></label>
                <input type="text" v-model.trim="$v.expiryDate.$model"
                       :class="{'is-invalid': validationStatus($v.expiryDate)}" class="form-control form-control-lg"
                       placeholder="JAN-2020">
                <div v-if="!$v.expiryDate.required" class="invalid-feedback">Please provide an expiryDate.</div>
            </div>
            <div class="col-12 form-group text-center">
                <button class="btn btn-vue btn-lg col-4">Add bank</button>
            </div>
        </div>
    </form>
</template>

<script>
    import {required} from 'vuelidate/lib/validators';
    import {bus, serverAPIPath} from "../main";

    export default {
        name: "NewBankForm",
        data() {
            return {
                "bankName": '',
                "cardNumber": '',
                "expiryDate": ''
            }
        },
        validations: {
            bankName: {required},
            cardNumber: {required},
            expiryDate: {required,}
        },
        methods: {
            validationStatus(validation) {
                return typeof validation != "undefined" ? validation.$error : false;
            },
            resetData() {
                this.bankName = '';
                this.cardNumber = '';
                this.expiryDate = '';
            },
            submit() {
                this.$v.$touch();
                if (this.$v.$pending || this.$v.$error) return;
                let bankData = {
                    bankName: this.bankName,
                    cardNumber: this.cardNumber,
                    expiryDate: new Date(this.expiryDate).getTime()
                };
                fetch(serverAPIPath + "addCustomerBank", {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(bankData)
                }).then(resp => resp.json())
                    .then(data => {
                        bus.$emit('updateBankTable', data);
                        console.log('Success: ', data);
                    })
                    .catch((error) => {
                        console.error('Error: ', error);
                    });
                this.$v.$reset();
                this.resetData();
            }
        }
    }
</script>

<style scoped>
    .btn-vue {
        background: #53B985;
        color: #31485D;
    }

    .col-form-label-lg {
        font-size: 15px;
    }

    .form-control-lg {
        font-size: 15px;
    }
</style>