package com.andre_enricco.visao.gui.servicoPrestado;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.format.DateTimeParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.andre_enricco.controller.ServicoPrestadoController;
import com.andre_enricco.util.FormatoUtils;

public class AdicionarServicoPrestado extends JPanel {

    private final ServicoPrestadoController controller;

    private final JTextField idServicoBusca = new JTextField(15);
    private final JTextField descricaoServico = new JTextField(15);
    private final JTextField valorCadastrado = new JTextField(15);

    private final JTextField idServicoPrestado = new JTextField(15);
    private final JTextField data = new JTextField(15);
    private final JTextField idPet = new JTextField(15);
    private final JTextField valorCobrado = new JTextField(15);

    private Integer idServicoSelecionado;

    public AdicionarServicoPrestado(ServicoPrestadoController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        descricaoServico.setEditable(false);
        valorCadastrado.setEditable(false);

        JButton botaoBuscar = new JButton("Buscar");
        botaoBuscar.addActionListener(e -> buscarServico());

        JButton botaoSalvar = new JButton("Salvar serviço prestado");
        botaoSalvar.addActionListener(e -> salvar());

        JPanel painelBusca = new JPanel(new GridLayout(2, 2, 8, 8));
        painelBusca.add(new JLabel("ID do serviço:"));
        painelBusca.add(idServicoBusca);
        painelBusca.add(new JLabel());
        painelBusca.add(botaoBuscar);

        JPanel painelServico = new JPanel(new GridLayout(2, 2, 8, 8));
        painelServico.add(new JLabel("Serviço escolhido:"));
        painelServico.add(descricaoServico);
        painelServico.add(new JLabel("Valor cadastrado:"));
        painelServico.add(valorCadastrado);

        JPanel painelBuscaEPreview = new JPanel(new BorderLayout(8, 8));
        painelBuscaEPreview.add(painelBusca, BorderLayout.NORTH);
        painelBuscaEPreview.add(painelServico, BorderLayout.CENTER);

        JPanel painelDadosPrestacao = new JPanel(new GridLayout(5, 2, 8, 8));
        painelDadosPrestacao.add(new JLabel("ID do serviço prestado:"));
        painelDadosPrestacao.add(idServicoPrestado);
        painelDadosPrestacao.add(new JLabel("Data (dd/MM/aaaa):"));
        painelDadosPrestacao.add(data);
        painelDadosPrestacao.add(new JLabel("ID do pet:"));
        painelDadosPrestacao.add(idPet);
        painelDadosPrestacao.add(new JLabel("Valor cobrado (use ponto para decimais):"));
        painelDadosPrestacao.add(valorCobrado);
        painelDadosPrestacao.add(new JLabel());
        painelDadosPrestacao.add(botaoSalvar);

        JPanel conteudo = new JPanel(new GridLayout(2, 1, 0, 12));
        conteudo.add(painelBuscaEPreview);
        conteudo.add(painelDadosPrestacao);
        add(conteudo, BorderLayout.NORTH);
    }

    private void buscarServico() {
        String idTexto = idServicoBusca.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o ID do serviço.");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            var servico = controller.buscarServicoParaPrestacao(id);

            idServicoSelecionado = servico.getId();
            descricaoServico.setText(servico.getDescricao());
            valorCadastrado.setText(String.format("R$ %.2f", servico.valorFinalDoServico()));
        } catch (NumberFormatException erro) {
            limparServicoSelecionado();
            JOptionPane.showMessageDialog(this, "Digite um ID de serviço válido.");
        } catch (IllegalArgumentException erro) {
            limparServicoSelecionado();
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }

    private void salvar() {
        if (idServicoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Busque um serviço antes de salvar.");
            return;
        }

        String idTexto = idServicoPrestado.getText().trim();
        String dataTexto = data.getText().trim();
        String idPetTexto = idPet.getText().trim();
        String valorTexto = valorCobrado.getText().trim();

        if (idTexto.isEmpty() || dataTexto.isEmpty() || idPetTexto.isEmpty() || valorTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos do serviço prestado.");
            return;
        }

        try {
            controller.adicionar(
                    Integer.parseInt(idTexto),
                    FormatoUtils.converterData(dataTexto),
                    Integer.parseInt(idPetTexto),
                    idServicoSelecionado,
                    Double.parseDouble(valorTexto));

            JOptionPane.showMessageDialog(this, "Serviço prestado cadastrado com sucesso.");
            limparFormulario();
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    "IDs precisam ser números e o valor cobrado deve usar ponto para decimais.");
        } catch (DateTimeParseException erro) {
            JOptionPane.showMessageDialog(this, "Use a data no formato dd/MM/aaaa.");
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }

    private void limparServicoSelecionado() {
        idServicoSelecionado = null;
        descricaoServico.setText("");
        valorCadastrado.setText("");
    }

    private void limparFormulario() {
        idServicoPrestado.setText("");
        data.setText("");
        idPet.setText("");
        valorCobrado.setText("");
        idServicoBusca.setText("");
        limparServicoSelecionado();
    }
}
