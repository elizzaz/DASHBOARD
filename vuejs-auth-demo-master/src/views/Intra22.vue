<template>
  <div class="container">
    <div class="card" id="carte">
      <span class="form-row">
      <p class="title"> <i> INTRANET EPITECH</i></p>

      <button v-if="binomes" @click="close"><img src="../assets/cancel.png" alt=""></button>

      <button @click="intra" class="button">Mes binômes</button>
</span>
      <span
        v-for="(binome, index) in binomes"
        :key="index"
      ><br />
        <h5>Projet/Nom du binôme</h5>
        <h3>{{ binome.activities }}</h3>
        <h3>{{ binome.login }}</h3>
        
      </span>
    </div>
    
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Intra",
  data() {
    return {
      binomes: null,
      
    };
  },
  methods: {
    intra() {
      axios
        .get(
          "https://intra.epitech.eu/auth-6d63b4f96f07cc22c867b12030dfe944e2f763eb/user/elisa.morillon%40epitech.eu/binome?format=json"
        )
        .then((res) => {
          console.log(res);
          this.binomes = res.data.binomes;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    
    close(){
      axios
        .delete(
          "https://intra.epitech.eu/auth-6d63b4f96f07cc22c867b12030dfe944e2f763eb/user/elisa.morillon%40epitech.eu/binome?format=json"
        )
        .then((res) => {
          console.log(res);
          this.binomes = null;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>

<style scoped>




img {
  width: 100px;
}
.title{
  border-bottom: 2px solid black;
  width: 100%;
  padding-bottom: 16px;
}

h3 {
  padding: 1rem;
  font-size: 1rem;
  font-weight: 500;
  width: 400px;
  background-color: rgba(240, 248, 255, 0.445);
  
  border-radius: 0.5rem;
}
h4 {
  padding: 1rem;
  font-size: 1rem;
  font-weight: 500;
  background-color: rgba(240, 248, 255, 0.445);
  margin: 2rem;
  border-radius: 0.5rem;
  width: 1000px;
}
img{
  width: 20px;
}
h5 {
  background-color: rgba(240, 248, 255, 0.445);
  padding: 0.5rem;
}
.button {
 
  background-color: aliceblue;
  box-shadow: 10px 10px 11px -1px rgba(0,0,0,0.38);
  color: black;
  width: 100%;
    margin-top: 20px;


border: none;

}
</style>
