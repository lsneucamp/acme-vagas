package co.acme.vagasapi.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistanciaService {

    private final List<Edge> allEdges;

    /**
     * Popula edges especificados na documentação do desafio
     *
     * https://github.com/VAGAScom/desafio-tecnico/blob/master/graph.png
     */
    public DistanciaService() {

        Edge AB = new Edge("A","B",5);
        Edge BA = new Edge("B","A",5);
        Edge BC = new Edge("B","C",7);
        Edge BD = new Edge("B","D",3);
        Edge CB = new Edge("C","B",7);
        Edge CE = new Edge("C","E",4);
        Edge DB = new Edge("D","B",3);
        Edge DE = new Edge("D","E",10);
        Edge DF = new Edge("D","F",8);
        Edge EC = new Edge("E","C",4);
        Edge ED = new Edge("E","D",10);
        Edge FD = new Edge("F","D",8);

        allEdges = Arrays.asList(
                AB,
                BA,
                BC,
                BD,
                CB,
                CE,
                DB,
                DE,
                DF,
                EC,
                ED,
                FD
        );

    }


    /**
     * Calcular a pontuação da distância
     * @param a ponto de origem
     * @param b ponto de destino
     * @return pontuação da distância
     */
    public Integer calcularPontuacaoDistancia(String a, String b){
        Integer menorDistancia  = calculaMenorDistacia(a,b);

        if(menorDistancia > 20) return 0;
        if(menorDistancia > 15) return 25;
        if(menorDistancia > 10) return 50;
        if(menorDistancia > 5) return 75;

        return 100;

    }




    /**
     * Calcula a menor distância entre dois pontos
     * @param a ponto de origem
     * @param b ponto de destino
     * @return menor distância
     */
    private Integer calculaMenorDistacia(String a, String b) {
        List<List<Edge>> list = walker(a, b);

        // retorna -1 caso o caminho não encontrado.
        if(list.isEmpty())
            return -1;

        // acha o menor caminho pela soma das distâncias
        Integer min = Integer.MAX_VALUE;
        for (List<Edge> edges : list) {
            Integer sum = edges.stream().mapToInt(Edge::getDistance).sum();
            if(sum < min){
                min = sum;
            }
        }

        return min;
    }

    private List<List<Edge>> walker(String sourceNode, String destNode) {
        return walker(sourceNode, destNode, null, null);
    }


    /**
     * Busca recursivamente todos os caminhos possiveis
     * @param sourceNode
     * @param destNode
     * @param currentPath
     * @param paths
     * @return lista de todos os caminhos
     */
    private List<List<Edge>> walker(String sourceNode, String destNode, List<Edge> currentPath, List<List<Edge>> paths) {
        if (currentPath == null) {
            currentPath = new ArrayList<>();
        }
        if (paths == null) {
            paths = new ArrayList<>();
        }

        if (sourceNode.equals(destNode)) {
            return paths;
        }


//        List<Edge> edges = findByNodeA(sourceNode);
        List<Edge> edges = allEdges.stream().filter(edge -> edge.nodeA.equals(sourceNode)).collect(Collectors.toList());


        for (Edge edge : edges) {
            if (!currentPath.contains(edge)&&!containsBidirectionalRef(currentPath,edge)) {
                List<Edge> forkPath = (List<Edge>) ((ArrayList<Edge>) currentPath).clone();

                forkPath.add(edge);

                if (edge.getNodeB().equals(destNode)) {
                    paths.add(forkPath);
                } else {
                    walker(edge.getNodeB(), destNode,forkPath,paths);
                }
            }
        }

        return paths;
    }

    /**
     * valida se o proximo edge já foi visitado
     * @param edges
     * @param nextEdge
     * @return retorna se já foi visitado
     */
    private boolean containsBidirectionalRef(List<Edge> edges, Edge nextEdge) {
        if (!edges.isEmpty()) {
            Edge last = edges.get(edges.size() - 1);
            return last.getNodeA().equals(nextEdge.getNodeB()) && nextEdge.getNodeA().equals(last.getNodeB());
        }
        return false;
    }


    @Data
    @AllArgsConstructor
    class Edge  {
        private String nodeA;
        private String nodeB;
        private Integer distance;
    }


}
