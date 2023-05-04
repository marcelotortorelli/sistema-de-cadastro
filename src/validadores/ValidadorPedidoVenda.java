package validadores;

import models.PedidoVenda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidadorPedidoVenda extends Validador{
    private  PedidoVenda pedidoVenda;
    public List<IValidadorPedidoVenda> validadoresPedidoVenda = new ArrayList<>();

    public ValidadorPedidoVenda(PedidoVenda pedidoVenda){
        this.pedidoVenda = pedidoVenda;
        this.validadoresPedidoVenda.add(new ValidadorDataValidade());
        this.validadoresPedidoVenda.add(new ValidadorQuantidade());

    }
    @Override
    public boolean ehValido() {
        return false;
    }
}