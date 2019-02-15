/**
 * UsuarioController
 *
 * @description :: Server-side actions for handling incoming requests.
 * @help        :: See https://sailsjs.com/docs/concepts/actions
 */

module.exports = {

  login: async (req, res) => {
    const parametros = req.allParams();
    var usuarioLogeado = await
      Usuario.find({
        where: {
          correo: parametros.correo,
          passwpr: parametros.passwpr,

        }
      });

    if (usuarioLogeado) {
      return res.ok(usuarioLogeado);
    }
    else {
      return res.badRequest({ mensaje: 'Usuario Invalido' });
    }
  },
};

