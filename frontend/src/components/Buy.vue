<template>
    <div class="custom-position">
        <div class="boxbox">
            <div class="total-wrapper">
                <h1>Total</h1>
                <div class="line-container">
                    <hr class="line" />
                </div>
                <h1>¥{{ propName | formatCurrency }}</h1>
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
    }
}
</script>
  
<style scoped>
.custom-position {
    position: absolute;
    top: 70%;
    left: 70%;
    transform: translate(-50%, -50%);
}

.boxbox {
    margin-top: 80px;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 400px;
    height: 200px;
    background-color: rgb(208, 208, 208);
    color: white;
    border-radius: 10px;
}

.total-wrapper {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

h1 {
    font-size: 40px;
}

.line-container {
    width: 80%;
}

.line {
    text-align: left;
    width: 100%;
    margin-top: 0px;
    border: none;
    border-top: 1px solid white;
}
</style>
  