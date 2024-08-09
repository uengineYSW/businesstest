<template>
    <v-card outlined>
        <v-card-title>
            회원가입
        </v-card-title>

        <v-card-text>
            <String label="이름" v-model="value.이름" :editMode="editMode"/>
            <Email offline label="이메일" v-model="value.이메일" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="회원가입"
            >
                회원가입
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
        name: '회원가입Command',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.이름 = '';
            this.value.이메일 = {};
        },
        watch: {
        },
        methods: {
            회원가입() {
                this.$emit('회원가입', this.value);
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

