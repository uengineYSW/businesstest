
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import PaymentPaymentManager from "./components/listers/PaymentPaymentCards"
import PaymentPaymentDetail from "./components/listers/PaymentPaymentDetail"

import Member회원Manager from "./components/listers/Member회원Cards"
import Member회원Detail from "./components/listers/Member회원Detail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/payments/payments',
                name: 'PaymentPaymentManager',
                component: PaymentPaymentManager
            },
            {
                path: '/payments/payments/:id',
                name: 'PaymentPaymentDetail',
                component: PaymentPaymentDetail
            },

            {
                path: '/members/회원',
                name: 'Member회원Manager',
                component: Member회원Manager
            },
            {
                path: '/members/회원/:id',
                name: 'Member회원Detail',
                component: Member회원Detail
            },



    ]
})
