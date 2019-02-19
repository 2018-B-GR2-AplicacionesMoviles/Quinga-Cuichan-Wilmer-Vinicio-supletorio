/**
 * InteraccionesTipoRecoleccion.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    oro_recolectado:{
      type: "string"
      },
      experiencia_recolectada:{
      type: "string"
      },
      idHijoPorUsuario:{
      model:'HijosPorUsuario'
      },

      idIngrediente:{
      model:'Ingredientes'
      }
  },

};

