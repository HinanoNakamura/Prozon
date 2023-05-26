<template>
  <div class="Protein recommendations for dogs">
    <div class="font">
      <div class="flexwan">
        <h1>★Employee recommended</h1>
        <div class="spacer"></div>
        <div class="testesprotein">
          <div class="column">
            <MiyazawaPro />
          </div>
          <div class="column">
            <NakamuraPro />
          </div>
          <div class="column">
            <InagakiPro />
          </div>
        </div>
        <div class="back-homes">
          <button class="button-74" v-if="$route.path !== '/'" @click="$router.push('/')">back</button>
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MiyazawaPro from "@/components/MiyazawaPro.vue";
import NakamuraPro from "@/components/NakamuraPro.vue";
import InagakiPro from "@/components/InagakiPro.vue";

export default {
  name: "EmployeeRecommended",
  components: {
    MiyazawaPro,
    NakamuraPro,
    InagakiPro
  },
};
</script>

<style>
.button-74 {
  font-family: 'Caveat', cursive;
  background-color: rgb(243, 227, 216);
  border: 2px solid #422800;
  border-radius: 30px;
  box-shadow: #422800 4px 4px 0 0;
  color: #422800;
  cursor: pointer;
  display: inline-block;
  font-weight: 50;
  font-size: 13px;
  padding: 0 18px;
  line-height: 20px;
  text-align: center;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.button-74:hover {
  background-color: #fff;
}

.button-74:active {
  box-shadow: #422800 2px 2px 0 0;
  transform: translate(2px, 2px);
}

body {
  max-width: 1800px;
  margin: auto;
}

.font {
  text-align: center;
  font-family: 'Caveat', cursive;
}

.spacer {
  height: 30px;
}

.testesprotein {
  display: flex;
  justify-content: flex-start; /* 左寄せに変更 */
  margin-left: 30px; /* 追加：左の余白を調整 */
  margin-right: 30px; /* 追加：右の余白を調整 */
}

.column {
  flex-basis: calc(33.33% - 30px); /* 画面幅の1/3にする */
}

.column:not(:last-child) {
  margin-right: 30px; /* カラム間の間隔を指定 */
}

.back-homes {
  position: absolute;
  bottom: -200px;
  left: 49%;
  transform: translateX(-0%);
}

</style>
