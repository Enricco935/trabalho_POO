package com.andre_enricco.visao.gui.servicoPrestado;

import com.andre_enricco.controller.ServicoPrestadoController;
import com.andre_enricco.model.ServicoPrestado;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;
import com.andre_enricco.visao.gui.modelos.TelaAtualizavel;

public class ListaServicosPrestados extends ModeloTabelaGenerica implements TelaAtualizavel {

    private final ServicoPrestadoController controller;

    public ListaServicosPrestados(ServicoPrestadoController controller) {
        super(new String[] {
            "ID",
            "Serviço",
            "Data",
            "Valor cobrado",
            "Pet",
            "Dono"
        });

        this.controller = controller;
        atualizar();
    }

    @Override
    public void atualizar() {
        limparLinhas();
        for (ServicoPrestado servicoPrestado : controller.listar()) {
            adicionarLinha(new Object[] {
                servicoPrestado.getId(),
                servicoPrestado.getServico() == null ? "" : servicoPrestado.getServico().getDescricao(),
                FormatoUtils.formatarData(servicoPrestado.getData()),
                servicoPrestado.getValorCobrado(),
                servicoPrestado.getPet() == null ? "" : servicoPrestado.getPet().getNome(),
                servicoPrestado.getPet() == null || servicoPrestado.getPet().getCliente() == null
                        ? ""
                        : servicoPrestado.getPet().getCliente().getNome()
            });
        }
    }
}
