<template>
  <div class="card">
    <h1 class="card__title">Espace Perso</h1>
    <p class="card__subtitle">Voilà donc qui je suis...</p>
    <p>{{email}}</p>
   <!-- <img :src="user.photo"/>-->
    <div class="form-row">
      <button @click="logout()" class="button">
        Déconnexion
      </button>
    </div>
  </div>
</template>


<script>

export default {
  name: 'Profile',
   data: function() {
    return {
      infos : {
 
      }
        
    };
  },

  mounted() {
  console.log(JSON.parse(localStorage.getItem('user')));
  console.log(JSON.parse(JSON.stringify(localStorage.getItem('user'))));
  
  
},
  methods: {
   
    logout: function () {
      this.$store.commit('logout');
      this.$router.push('/');
    }
  }
}
</script>

<style scoped>

</style>>

