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
  tipoIngredienteUtilizados :{
  		type:"string"
  },
  necesitaRefrigeracion :{
    		type:"boolean"
  },
  comidaId:{
      		type:"number"
  },
  hijosPorUsuarios:{
  collection:'HijosPorUsuario',
  via:'idIngredientes'

  },

  comidaId:{
  model:'Comida'
  },

  	tipoIngrediente:{
  		type:'string',
  		isIn:['batalla','recoleccion','personaje']
  	},

  		interaccionesTipoBatalla:{
    		collection:'InteraccionesTipoBatalla',
    		via:'idIngredientes'
    	},

    		interaccionesTipoRecoleccion:{
      		collection:'InteraccionesTipoRecoleccion',
      		via:'idIngrediente'
      	}

  },
};

