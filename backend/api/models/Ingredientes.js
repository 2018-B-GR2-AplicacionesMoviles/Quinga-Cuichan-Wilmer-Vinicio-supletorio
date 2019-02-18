/**
 * Ingredientes.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
  idIngredientes:{
    type: "number"

  },

  nombreIngrediente:{
  type: "string"
  },
  cantidad :{
    type: "number"
  },
  descripcionPreparacion :{
      type: "string"
  },
  opcional :{
  		type:"boolean"
  },
  tipoIngrediente :{
  		type:"string"
  },
  necesitaRefrigeracion :{
    		type:"boolean"
  },
  comidaId:{
      		type:"number"
  },
  }





};

