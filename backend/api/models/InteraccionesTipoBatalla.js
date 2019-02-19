/**
 * InteraccionesTipoBatalla.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

	clima:{
		type:'number'
	},
	turnosJugados:{
		type:'number'
	},
	recompensaOro:{
		type:'number'
	},
	recompensaExp:{
		type:'number'
	},
	estado:{
		type:'boolean'
	},

	idIngredientes:{
	model:'Ingredientes'
	},

	hijosBatalla:{
	collection:'HijosBatalla',
	via:'idInTiBatalla'
	}
  },

};

