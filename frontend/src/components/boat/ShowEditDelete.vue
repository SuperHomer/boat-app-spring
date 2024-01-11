<script setup>
import { ref, watch, onMounted } from 'vue'
import axios from 'axios'
import { useRoute } from 'vue-router'

const route = useRoute()


const mode = ref(1)
const messageSuccess = ref('')
const messageError = ref('')

const test = ref('')



onMounted(() => {
    getBoat()
})


watch(mode, () => {
    console.log(mode.value)
    if (mode.value !== 1 && mode.value !== 4) {
        messageSuccess.value = ""
        messageError.value = ""
    }
    if (mode.value === 1) {
        getBoat()
    }
})

const newBoat = ref({
    name: '',
    description: '',
    image_path: ''
})



function getBoat() {
    axios.get('/api/v1/boats/' + route.params.id)
      .then(function (response) {
        console.log(response);
        newBoat.value = response.data
      })
      .catch(function (error) {
        console.log(error);
      });
}

function editBoat() {
    console.log(newBoat.value)
    axios.put('/api/v1/boats/' + route.params.id, {
        name: newBoat.value.name,
        description: newBoat.value.description,
        image_path: newBoat.value.image_path
      })
      .then(function (response) {
        console.log(response);
        messageSuccess.value = "Boat edited successfully !"
        mode.value = 1
      })
      .catch(function (error) {
        console.log(error);
        messageError.value = parseError(error)
      });
}

function deleteBoat() {
    axios.delete('/api/v1/boats/' + route.params.id)
      .then(function (response) {
        console.log(response);
        messageSuccess.value = "Boat deleted successfully !"
        mode.value = 4
      })
      .catch(function (error) {
        messageError.value = parseError(error)
      });
}

function parseError(error) {
    let msg = ""
    if (error.response) {
        if (error.response.status === 422) {
            if (error.response.data.errors.name)
                msg += error.response.data.errors.name[0] + " "
            if (error.response.data.errors.description)
                msg += error.response.data.errors.description[0] + " "
            return msg
        }
        return error.response.data.message
    }
    return error.message
}

function goToBoats() {
    window.location.href = '/boats';
}
</script>

<template>
    <div>
        <div class="alert alert-success" v-show="messageSuccess !== ''">
            {{ messageSuccess }}
        </div>
        <div class="alert alert-danger" v-show="messageError !== ''">
            {{ messageError }}
        </div>
        <div class="">
            <router-link to="/"><button class="btn">Go back to boats</button></router-link>
        </div>
        <br>
        <div class="card" v-show="mode !== 4">
            <div class="container" v-show="mode === 1">
                <h1>{{ newBoat.name }}</h1>
                <img class="boat-img" :src="newBoat.image_path" :alt="newBoat.name" />
                <p>{{ newBoat.description }}</p>
                <div class="action">
                    <button class="btn" @click="mode=2">Edit</button>
                    <button class="btn" @click="mode=3">Delete</button>
                </div>
            </div>
            <div class="container"  v-show="mode === 2">
                <h1>Edit boat</h1>
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
                    <button class="btn" @click="editBoat()">Save</button>
                    <button class="btn" @click="mode=1">Cancel</button>
                </div>
            </div>
            <div class="container" v-show="mode === 3">
                <h1>Delete</h1>
                <h3>Are you sure you want to delete this item ?</h3>
                <div class="action">
                    <button class="btn" @click="deleteBoat()">Yes</button>
                    <button class="btn" @click="mode=1">No</button>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
    
</style>
