<template>
  <div class="container">
    
        <button @click="logout()" class="buttonn">Déconnexion</button>
      
    <div class="card" id="pcl">
      
        <h1>Mon tableau de bord</h1>
              <h3>Bienvenue</h3>

                 <p class="p"> <i> Localisation:</i>  <br>{{ville}} <br> {{pays}}</p>

      

      
     
      
      <h4>Voici les différents services que tu peux configurer</h4>
      
        <span class="form-row" id="menu"><img  src="../assets/Google.png" alt="">
          <p>GOOGLE SERVICES</p>
          <i>Drive <br> agenda, ...</i>
        </span>
      
      
        <span class="form-row" id="menu"><img  src="../assets/epitech.png" alt="">
          <p >INTRA EPITECH</p>
          <i>Notifs <br> binômes, ...</i>
        </span>

      
      
    
    
    </div>
    <div class="card" id="profil">
      <p >Mon Profil</p>

      <span class="form-row">
        <h5><i>Nom</i></h5>
        
        <h4 class="form-row__input">{{ title }}</h4>
      </span>
      <span class="form-row">
        <h5><i>Email</i></h5>
        <h4 class="form-row__input">{{ login }}</h4>
      </span>

      <span class="form-row">
        <h5><i>Promo</i></h5>
        <h4 class="form-row__input">{{ promo }}</h4>
      </span>
      <span class="form-row">
        <h5><i>Ville</i></h5>
        <h4 class="form-row__input">{{ location }}</h4>
      </span>
      <span class="form-row">
        <h5><i>Cursus</i></h5>
        <h4 class="form-row">{{ school_title }}</h4>
      </span>
      
    </div>
    

    <div>
     
        <Intra/>
      
    </div> <div>
      
        <Intra22 />
     
    </div>
    
    
    
    
  </div>
</template>

<script>
import Intra from "./Intra.vue";
import Intra22 from "./Intra22.vue";


import axios from "axios";
export default {
  name: "Dashperso",
  components: {
    Intra,
    Intra22
  },
  mounted: function() {
    axios
      .get(
        "https://intra.epitech.eu/auth-6d63b4f96f07cc22c867b12030dfe944e2f763eb/user/elisa.morillon%40epitech.eu/?format=json"
      )
      .then((res) => {
        console.log(res);
        this.title = res.data.title;
        this.login = res.data.login;
        this.promo = res.data.promo;
        this.location = res.data.location;
        this.school_title = res.data.school_title;
      })
      .catch((e) => {
        console.log(e); 
      });
      
     axios.get("https://api.userinfo.io/userinfos")
     .then((res) => {
        console.log(res);
        this.ville = res.data.city.name;
        this.pays = res.data.country.name;
      })
      .catch((e) => {
        console.log(e);
      });
     
  },

  data() {
    return {
      title: null,
      login: null,
      promo: null,
      location: null,
      school_title: null,
    };
  },

  methods: {

    logout: function () {
      this.$router.push('/login');
    }
  },
   
}
</script>

<style scoped>
.card {
  margin: 20px;
  
}
#carte {
  width: 365px;
  height: 200px;
}
#carte1 {
  width: 500px;
  height: 300px;
  margin-top: -250px;
}
.form-row {
  display: inline-block;
  margin: 16px;
  
}


.form-row {
  display: flex;
  margin: 16px 0px;
  gap: 16px;
  flex-wrap: wrap;
}
i{
  color:  rgba(125, 130, 134, 0.445);
  margin-right: 200px;
  
  
}
.buttonn{
  width: 20%;
  background: #df5e78;
    color:white;
    border-radius: 8px;
    font-weight: 800;
    font-size: 15px;
    border: none;
  float: right;
  margin-top: -45px;
    padding: 12px;
    transition: .4s background-color;
}
p {
   
  color: #0f1724;
  border: 1px solid #0f1724;
  border-radius: 8px;
  font-weight: 800;
  font-size: 15px;
  padding: 16px;
  transition: 0.4s background-color;
}

#menu{box-shadow: 1px 0px 10px 4px rgba(95,93,93,0.28);
  border-radius: 1rem;
  padding: 2rem;
  margin: 40px;
  
}
.p {
  box-shadow: 1px 0px 10px 4px rgba(95,93,93,0.28);
  font-weight: 800;
  font-size: 15px;
  float: right;
 width: 25%;
 margin-top: -76px;
  padding: 13px;
  transition: 0.4s background-color;
   border: none;
}
.container {
  padding: 35px 15px 5px;
  width: 1200px;
  height: 1600px;
    background-image: linear-gradient(62deg, #FBAB7E 0%, #F7CE68 100%);
}

.container div {
  float: left;
  width: 300px;
  height: 600px;
}

.button {
  background-color: rgba(198, 199, 201, 0.445);
  color: black;
  
}
#pcl {
  width: 780px;
  height: 500px;
  

}

#profil {
  
  height: 500px;
}

img {
  width: 50px;
  height: 50px;
}
</style
>
