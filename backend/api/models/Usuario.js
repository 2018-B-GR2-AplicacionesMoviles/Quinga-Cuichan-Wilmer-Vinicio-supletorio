/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
 nombre:{
  type: 'string'
  },
  apellido:{
    type: 'string'
  },
  correo:{
      type: 'string'
  },
  fechaNacimiento:{
  		type:'string'
  },
  password:{
  		type:'string'
  },
  total_oro:{
  type:'integer'
  },
  total_experiencia:{
  type:'integer'
  }

  },

};

