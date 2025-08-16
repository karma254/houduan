<template>

</template>

<style type="text/scss" scoped lang="scss">
    .footer{
        padding: 15px;
        color: #767676;
        text-align: center;
        .link{
            a:after{
                content: ' | ';
            }
            a:last-child:after{
                content: '';
            }
        }
    }
</style>

