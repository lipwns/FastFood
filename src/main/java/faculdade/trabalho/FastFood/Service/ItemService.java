package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.IngredienteModel;
import faculdade.trabalho.FastFood.Model.ItemIngredienteModel;
import faculdade.trabalho.FastFood.Model.ItemModel;
import faculdade.trabalho.FastFood.Repository.IngredienteRepository;
import faculdade.trabalho.FastFood.Repository.ItemIngredienteRepository;
import faculdade.trabalho.FastFood.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final IngredienteRepository ingredienteRepository;
    private final ItemIngredienteRepository itemIngredienteRepository;


    //criar item
    public ItemModel criarItem(ItemModel item) {

        if (item == null || item.getIngredientes() == null) {
            System.out.println("Item inválido.");
            return null;
        }


        item.getIngredientes().removeIf(lig ->
                lig == null || lig.getIngrediente() == null ||
                        (lig.getIngrediente().getId() == null && lig.getIngrediente().getNome() == null)
        );

        ItemModel novoItem = new ItemModel();
        novoItem.setNome(item.getNome());
        novoItem.setDescricao(item.getDescricao());
        novoItem.setPreco(item.getPreco());

        List<ItemIngredienteModel> ligacoes = new ArrayList<>();

        for (ItemIngredienteModel lig : item.getIngredientes()) {

            IngredienteModel ing = null;

            // Procurar ingrediente por ID
            if (lig.getIngrediente() != null && lig.getIngrediente().getId() != null) {
                Optional<IngredienteModel> opt = ingredienteRepository.findById(lig.getIngrediente().getId());
                if (opt.isPresent()) ing = opt.get();
            }

            // Procurar ingrediente por nome
            if (ing == null && lig.getIngrediente() != null && lig.getIngrediente().getNome() != null) {
                ing = ingredienteRepository.findByNomeIgnoreCase(lig.getIngrediente().getNome())
                        .orElse(null);
            }

            if (ing == null) {
                System.out.println("Ingrediente não encontrado!");
                return null;
            }

            ItemIngredienteModel novaLigacao = new ItemIngredienteModel();
            novaLigacao.setIngrediente(ing);
            novaLigacao.setQuantidade(lig.getQuantidade());
            novaLigacao.setItem(novoItem);

            ligacoes.add(novaLigacao);
        }

        novoItem.setIngredientes(ligacoes);
        return itemRepository.save(novoItem);
    }


    //listar todos
    public List<ItemModel> listarTodos() {
        return itemRepository.findAll();
    }


    //buscar por id
    public ItemModel buscarPorId(Long id) {
        return itemRepository.findById(id).orElse(null);
    }


    //atualizar
    public ItemModel atualizarItem(Long id, ItemModel item) {

        ItemModel existente = itemRepository.findById(id).orElse(null);

        if (existente == null) {
            System.out.println("Item não encontrado para atualizar!");
            return null;
        }

        if (item.getIngredientes() != null) {
            item.getIngredientes().removeIf(lig ->
                    lig == null ||
                            lig.getIngrediente() == null ||
                            (lig.getIngrediente().getId() == null && lig.getIngrediente().getNome() == null)
            );
        }

        existente.setNome(item.getNome());
        existente.setDescricao(item.getDescricao());
        existente.setPreco(item.getPreco());

        // Lista nova validada
        List<ItemIngredienteModel> novasLigacoes = new ArrayList<>();

        if (item.getIngredientes() != null) {
            for (ItemIngredienteModel lig : item.getIngredientes()) {

                IngredienteModel ing = null;

                // Procurar ingrediente por ID
                if (lig.getIngrediente() != null && lig.getIngrediente().getId() != null) {
                    ing = ingredienteRepository.findById(lig.getIngrediente().getId()).orElse(null);
                }

                // Procurar ingrediente por nome
                if (ing == null && lig.getIngrediente() != null && lig.getIngrediente().getNome() != null) {
                    ing = ingredienteRepository.findByNomeIgnoreCase(lig.getIngrediente().getNome())
                            .orElse(null);
                }

                if (ing == null) {
                    System.out.println("Ingrediente inválido na atualização.");
                    return null;
                }

                ItemIngredienteModel novaLig = new ItemIngredienteModel();
                novaLig.setIngrediente(ing);
                novaLig.setQuantidade(lig.getQuantidade());
                novaLig.setItem(existente);

                novasLigacoes.add(novaLig);
            }
        }

        existente.getIngredientes().clear();

        for (ItemIngredienteModel novo : novasLigacoes) {
            existente.getIngredientes().add(novo);
        }

        return itemRepository.save(existente);
    }

    //excluir
    public void excluir(Long id) {
        if (!itemRepository.existsById(id)) {
            System.out.println("Item não encontrado para deletar!");
        }

        itemRepository.deleteById(id);
        System.out.println("Item deletado com sucesso!");
    }
}