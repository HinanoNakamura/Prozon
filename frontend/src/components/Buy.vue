<template>
    <div class="custom-position">
        <div class="boxbox">
            <div class="total-wrapper">
                <h1>Total</h1>
                <div class="line-container">
                    <hr class="line" />
                </div>
                <h1>¥{{ propName | formatCurrency }} <button class="button-53" @click="useCoupon">Use Coupon</button></h1>
               
            </div>
        </div>



    </div>
</template>
  
<script>
export default {
    name: 'BuyCom',
    props: ['propName'],
    filters: {
        formatCurrency(value) {
            return value.toLocaleString();
        }
    },
    methods:{
        useCoupon(){
            this.$emit('button-click');
        }
    }
}
</script>
  
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Caveat&display=swap');
.custom-position {
    position: absolute;
    top: 85%;
    left: 65%;
    transform: translate(-50%, -50%);
}

.boxbox {
    margin-top: 80px;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 350px;
    height: 200px;
    background-color: rgb(246, 246, 246);
    color: rgb(0, 0, 0);
    border-radius: 10px;
    padding-right: 30px;
    padding-left: 30px;
    position: relative;
}

.total-wrapper {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

h1 {
    font-size: 35px;
    text-align: left;
}

.line-container {
    width: 100%;
}

.line {
    text-align: left;
    width: 100%;
    margin-top: 0px;
    border: none;
    border-top: 1px solid rgb(0, 0, 0);
}

.button-53 {
    background-color: rgb(227, 206, 220);
    border: 0 solid #E5E7EB;
    box-sizing: border-box;
    color: #000000;
    display: inline-block;
    vertical-align: middle;
    position: absolute;
    top: -55px;
    left: 150px;

    margin-right: 40px;
    margin-left: 40px;
    font-size: 1rem;
    font-weight: 700;
    justify-content: center;
    line-height: 1rem;
    padding: .75rem 1.65rem;
    position: relative;
    text-align: center;
    text-decoration: none #000000 solid;
    text-decoration-thickness: auto;
    width: 100%;
    max-width: 150px;
    position: relative;
    cursor: pointer;
    transform: rotate(-2deg);
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
    font-family: 'Caveat', cursive;
}

.button-53:focus {
    outline: 0;
}

.button-53:after {
    content: '';
    position: absolute;
    border: 1px solid #000000;
    bottom: 4px;
    left: 4px;
    width: calc(100% - 1px);
    height: calc(100% - 1px);
}

.button-53:hover:after {
    bottom: 2px;
    left: 2px;
}
</style>
  