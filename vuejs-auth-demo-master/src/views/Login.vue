<template>
  <div class="card">
    <h1 class="card__title" v-if="mode == 'login'">Connexion</h1>
    <h1 class="card__title" v-else>Inscription</h1>
    <p class="card__subtitle" v-if="mode == 'login'">
      Tu n'as pas encore de compte ?
      <span class="card__action" @click="switchToCreateAccount()"
        >Créer un compte</span
      >
    </p>
    <p class="card__subtitle" v-else>
      Tu as déjà un compte ?
      <span class="card__action" @click="switchToLogin()">Se connecter</span>
    </p>
    <div class="form-row">
      <input
        v-model="email"
        class="form-row__input"
        type="text"
        placeholder="Adresse mail"
      />
    </div>
    <div class="form-row" v-if="mode == 'create'">
      <input
        v-model="first_name"
        class="form-row__input"
        type="text"
        placeholder="first_name"
      />
      <input
        v-model="name"
        class="form-row__input"
        type="text"
        placeholder="name"
      />
        <input
        v-model="city"
        class="form-row__input"
        type="text"
        placeholder="City"
      />
    </div>
    <div class="form-row">
      <input
        v-model="password"
        class="form-row__input"
        type="password"
        placeholder="Mot de passe"
      />
    </div>
    <div class="form-row" v-if="mode == 'login' && status == 'error_login'">
      Adresse mail et/ou mot de passe invalide
    </div>
    <div class="form-row" v-if="mode == 'create' && status == 'error_create'">
      Adresse mail déjà utilisée
    </div>
    <div class="form-row">
      <button
        @click="logine()"
        class="button"
        :class="{ 'button--disabled': !validatedFields }"
        v-if="mode == 'login'"
      >
        <span v-if="status == 'loading'">Connexion en cours...</span>
        <span v-else>Connexion</span>
      </button>
      <button
        @click="createAccounte()"
        class="button"
        :class="{ 'button--disabled': !validatedFields }"
        v-else
      >
        <span v-if="status == 'loading'">Création en cours...</span>
        <span v-else>Créer mon compte</span>
      </button>
      <p>Connexion avec Google</p>
      <div class="g-signin2" data-onsuccess="onSignIn">
      </div>

    </div>
  </div>
</template>
<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
  name: "Login",
  data: function() {
    return {
      mode: "login",
      email: "",
      first_name: "",
      name: "",
      password: "",
      city: "",
    };
  },
  computed: {
    validatedFields: function() {
      if (this.mode == "create") {
        if (
          this.email != "" &&
          this.first_name != "" &&
          this.name != "" &&
          this.password != "" &&
          this.city != ""
        ) {
          return true;
        } else {
          return false;
        }
      } else {
        if (this.email != "" && this.password != "") {
          return true;
        } else {
          return false;
        }
      }
    },
    ...mapState(["status"]),
  },
  methods: {
   
    switchToCreateAccount: function() {
      this.mode = "create";
    },
    switchToLogin: function() {
      this.mode = "login";
    },

    
    
    createAccounte: async function() {
     axios.post( 'http://localhost:8080/api/user', {
      email : this.email,
      password : this.password,
      city : this.city,
      first_name : this.first_name,
      name : this.name,
    })
    .then(function (reponse) {
      
    localStorage.setItem(
          "user",
          JSON.stringify(reponse.data)
        );
      //On traite la suite une fois la réponse obtenue 
      console.log(reponse);
    })
    .catch(function (erreur) {
      //On traite ici les erreurs éventuellement survenues
      console.log(erreur);
    });
    },
    
    logine: async function() {
      let email = this.email;
      let password = this.password;
      let result = await axios.post(
        `http://localhost:8080/api/login/${email}`,{password},
           console.log(this.email),
            console.log(this.password),
      );
     
        localStorage.setItem(
          "user",
          JSON.stringify(result.data)
        );
        this.$router.push("/dashperso");
      
      console.log(result);
    },
  },
 onSignIn(googleUser) {
   console.log(googleUser.getBasicProfile());
       
    }


};
</script>
<style scoped>
.form-row {
  display: flex;
  margin: 16px 0px;
  gap: 16px;
  flex-wrap: wrap;
}
.form-row__input {
  padding: 8px;
  border: none;
  border-radius: 8px;
  background: #f2f2f2;
  font-weight: 500;
  font-size: 16px;
  flex: 1;
  min-width: 100px;
  color: black;
}
.form-row__input::placeholder {
  color: #aaaaaa;
}</style
>>