<template>
</template>
<style type="text/scss" lang="scss">

</style>
<script>
    export default {
        name: "e-collect",
        data() {
            return {
            }
        },
        props:{
            value:[Boolean,Number],
            module:String,
            id:[String,Number],
            ziduan:[String,Number],
        },
        watch: {},
        computed: {
            isCollect(){
                return this.value;
            }
        },
        methods: {
            collect(){
                this.$collect(this.module , this.ziduan , this.id).then(res=>{
                    this.$emit('input' , true);
                });
            }
        },
        created() {

        },
        mounted() {
        },
        destroyed() {
        }
    }
</script>
