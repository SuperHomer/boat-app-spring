<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRoute } from 'vue-router'

const route = useRoute()

const newBoat = ref({
    name: '',
    description: '',
    image_path: ''
})

const messageSuccess = ref('')
const messageError = ref('')

function createBoat() {
    axios.post('/api/v1/boats', {
        name: newBoat.value.name,
        description: newBoat.value.description,
        image_path: newBoat.value.image_path
      })
      .then(function (response) {
        console.log(response);
        messageSuccess.value = "Boat created successfully !"
        messageError.value = ""
      })
      .catch(function (error) {
        console.log(error);
        messageError.value = parseError(error)
        messageSuccess.value = ""
      });
}

function parseError(error) {
    let msg = ""
    if (error.response) {
        if (error.response.status === 400) {
            console.log(error.response.data)
            if (error.response.data.name)
                msg += error.response.data.name + ", "
            if (error.response.data.description)
                msg += error.response.data.description + " "
            return msg
        }
        return error.response.data.message
    }
    return error.message
}

</script>


<template>
    <div>
        <div class="alert alert-success" v-show="messageSuccess !== ''">
            {{ messageSuccess }}
        </div>
        <div class="alert alert-danger" v-show="messageError !== ''">
            Error: {{ messageError }}
        </div>
        <div class="">
            <router-link to="/"><button class="btn">Go back to boats</button></router-link>
        </div>
        <br>
        <div class="card">
            <div class="container">
                <h1>Create boat</h1>
                <div class="form">
                    <label for="">Name</label>
                    <input type="text" v-model="newBoat.name" />
                </div>
                <div class="form">
                    <label for="">Image path (URL)</label>
                    <input type="text" v-model="newBoat.image_path"  />
                </div>
                <div class="form">
                    <label for="">Description</label><br>
                    <textarea rows="5" cols="50" id="multiLineInput" v-model="newBoat.description"></textarea>
                </div>
                <div class="action">
                    <button class="btn" @click="createBoat()">Create</button>
                    <router-link to="/"><button class="btn">Cancel</button></router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
</style>