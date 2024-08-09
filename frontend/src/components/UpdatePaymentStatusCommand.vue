<template>
    <v-card outlined>
        <v-card-title>
            UpdatePaymentStatus
        </v-card-title>

        <v-card-text>
            <String label="PaymentId" v-model="value.paymentId" :editMode="editMode"/>
            <String label="PaymentStatus" v-model="value.paymentStatus" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="updatePaymentStatus"
            >
                UpdatePaymentStatus
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
        name: 'UpdatePaymentStatusCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.paymentId = '';
            this.value.paymentStatus = '';
        },
        watch: {
        },
        methods: {
            updatePaymentStatus() {
                this.$emit('updatePaymentStatus', this.value);
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

