<template>
  <file-picker-button :config="gConfig" @picked="showDetails">
    Open Google Drive Dialog
  </file-picker-button>
</template>
<script>
import FilePickerButton from "vue-google-picker";
export default {
  components: {
    FilePickerButton
  },
  created() {
    this.gConfig = {
      // The Browser API key obtained from the Google API Console.
      developerKey: 'AIzaSyDK9hekGNqlU38JGdyFhqmdcqeXAxwysbk',

      // The Client ID obtained from the Google API Console. Replace with your own Client ID.
      clientId: '385016681435-nsdcpcra7ts7ccb084lh0d6dtk03iphv.apps.googleusercontent.com',

      // Scope to use to access user's drive.
      scope: 'https://www.googleapis.com/auth/drive.file'
    }
  }, 
  methods: {
    showDetails (data) {
     if(data.picked === 'picked') {
      console.log(data.docs)
     }
    }
  }
}
</script>