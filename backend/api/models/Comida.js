/**
 * Comida.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
  nombrePlato:{
  type: "string"
  },
  descripcionPlato:{
    type: "string"
  },
  nacionalidad:{
      type: "string"
  },
  numeroPersonas:{
  		type:"number"
  },
  picante:{
  		type:"boolean"
  }
  },

  /////relaciones one to many
 // ingredientes:{
  //collection:'ingrediente'
  //via: 'owner'
 // }

};

