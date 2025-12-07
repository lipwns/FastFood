package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.ItemIngredienteModel;
import faculdade.trabalho.FastFood.Repository.ItemIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemIngredienteService {

    @Autowired
    private ItemIngredienteRepository itemIngredienteRepository;

    // Criar relação item-ingrediente
    public ItemIngredienteModel adicionar(ItemIngredienteModel obj) {
        if (obj == null) {
            System.out.println("Objeto inválido.");
            return null;
        }
        System.out.println("Ingrediente vinculado ao item.");
        return itemIngredienteRepository.save(obj);
    }

    // Listar todas relações
    public List<ItemIngredienteModel> listarTodos() {
        return itemIngredienteRepository.findAll();
    }

    // Buscar por ID
    public ItemIngredienteModel buscarPorId(Long id) {
        if (id == null) {
            System.out.println("ID inválido.");
            return null;
        }
        return itemIngredienteRepository.findById(id).orElse(null);
    }

    // Atualizar quantidade
    public ItemIngredienteModel atualizar(Long id, ItemIngredienteModel novo) {
        if (id == null || novo == null) {
            System.out.println("Dados inválidos para atualização.");
            return null;
        }

        ItemIngredienteModel existente = itemIngredienteRepository.findById(id).orElse(null);

        if (existente == null) {
            System.out.println("Relação item-ingrediente não encontrada.");
            return null;
        }

        existente.setQuantidade(novo.getQuantidade());
        System.out.println("Quantidade atualizada!");

        return itemIngredienteRepository.save(existente);
    }

    // Excluir relação
    public boolean excluir(Long id) {
        if (id == null) {
            System.out.println("ID inválido.");
            return false;
        }

        ItemIngredienteModel obj = itemIngredienteRepository.findById(id).orElse(null);

        if (obj == null) {
            System.out.println("Relação item-ingrediente não encontrada.");
            return false;
        }

        itemIngredienteRepository.deleteById(id);
        System.out.println("Relação item-ingrediente removida.");
        return true;
    }
}