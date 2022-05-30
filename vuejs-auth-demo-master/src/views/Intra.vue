<template>
  <div class="container">
    <div class="card" id="carte">  
      <span class="form-row">
      <p class="title"> <i> INTRANET EPITECH</i></p>
      <button v-if="notifs" @click="close2"><img src="../assets/cancel.png" alt=""></button>
          <button @click="notif" class="button">Mes notifs</button> 
          </span>

      <span v-for="(notif, index) in notifs" :key="index">
        <h4>{{ notif.title }}</h4>
      </span>
    </div>
    <div >
    
        
      
     
    </div>
    
    
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Intra",
  data() {
    return {
      
      notifs: null,
    };
  },
  methods: {
    
    notif() {
      axios
        .get(
          "https://intra.epitech.eu/auth-6d63b4f96f07cc22c867b12030dfe944e2f763eb/user/elisa.morillon%40epitech.eu/notification/message?format=json"
        )
        .then((res) => {
          console.log(res);
          this.notifs = res.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    
    close2(){
      axios
        .delete(
          "https://intra.epitech.eu/auth-6d63b4f96f07cc22c867b12030dfe944e2f763eb/user/elisa.morillon%40epitech.eu/notification/message?format=json"
        )
        .then((res) => {
          console.log(res);
          this.notifs = null;
        })
        .catch((e) => {
          console.log(e);
        });
    }
  },
};
</script>

<style scoped>




img {
  width: 100px;
}
#carte {
  width: 100%;
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
  background-color: rgba(185, 125, 34, 0.829);
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
