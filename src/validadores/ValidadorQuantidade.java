package validadores;

import models.PedidoVenda;

public class ValidadorQuantidade implements IValidadorPedidoVenda {

    @Override
    public String validar(PedidoVenda pedidoVenda){
        if (pedidoVenda.getQuantidade() > pedidoVenda.getEstoque().getQuantidade()){
        return "Quantidade Indisponível em Estoque.";
        }

        return null;
    }

}
