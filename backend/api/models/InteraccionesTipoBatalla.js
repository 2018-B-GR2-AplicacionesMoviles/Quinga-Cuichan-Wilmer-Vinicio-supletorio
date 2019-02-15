/**
 * InteraccionesTipoBatalla.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombreIngredientes:{
      type: "string"
      },
      cantidad:{
        type: "number"
      },
      descriptionPreparacion:{
          type: "string"
      },
      opcional:{
      		type:"boolean"
      },
      tipoIngrediente :{
      		type:"string"
      },
      necesitaRefrigeracion:{
      type:"boolean"
      },
      comidaId:{
     type:"number"
      },

    //  tipoTablaHijo:{

      //}
  },

};

