package com.andre_enricco.visao.gui.servicoPrestado;

import com.andre_enricco.controller.ServicoPrestadoController;
import com.andre_enricco.model.ServicoPrestado;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class ListaServicosPrestados extends ModeloTabelaGenerica {

    public ListaServicosPrestados(ServicoPrestadoController controller) {
        super(new String[] {
            "ID",
            "Serviço",
            "Data",
            "Valor cobrado",
            "Pet",
            "Dono"
        });

        for (ServicoPrestado servicoPrestado : controller.listar()) {
            adicionarLinha(new Object[] {
                servicoPrestado.getId(),
                servicoPrestado.getServico() == null ? "" : servicoPrestado.getServico().getDescricao(),
                servicoPrestado.getData(),
                servicoPrestado.getValorCobrado(),
                servicoPrestado.getPet() == null ? "" : servicoPrestado.getPet().getNome(),
                servicoPrestado.getPet() == null || servicoPrestado.getPet().getCliente() == null
                        ? ""
                        : servicoPrestado.getPet().getCliente().getNome()
            });
        }
    }
}
