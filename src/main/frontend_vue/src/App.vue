<template>
    <div id="app">
        <h1>Customer Banks</h1>
        <NewBankForm/>
        <br />
        <CSVParser/>
        <br />
        <br />
        <CustomerBankTable/>
    </div>
</template>
<script>
    import CustomerBankTable from "./components/CustomerBankTable";
    import NewBankForm from "./components/NewBankForm";
    import CSVParser from "./components/CSVParser";

    export default {
        name: 'app',
        components: {
            CSVParser,
            CustomerBankTable,
            NewBankForm
        },
    }
</script>

<style>
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
