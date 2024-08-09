<template>
    <v-card outlined>
        <v-card-title>
            CreatePayment
        </v-card-title>

        <v-card-text>
            <Money offline label="PaymentAmount" v-model="value.paymentAmount" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="createPayment"
            >
                CreatePayment
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'CreatePaymentCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.paymentAmount = {};
        },
        watch: {
        },
        methods: {
            createPayment() {
                this.$emit('createPayment', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

