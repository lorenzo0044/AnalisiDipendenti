import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


        List<Dipendente> dipendenti = new ArrayList<>();
        dipendenti.add(new Dipendente("Marco", 19, 12266, Dipartimento.HR));
        dipendenti.add(new Dipendente("Pietro", 29, 150569, Dipartimento.IT));
        dipendenti.add(new Dipendente("Lisa", 57, 23200, Dipartimento.FINANCE));
        dipendenti.add(new Dipendente("Cristian", 34, 76000, Dipartimento.SALES));
        dipendenti.add(new Dipendente("Manuel", 40, 46000, Dipartimento.IT));
        dipendenti.add(new Dipendente("Gino", 35, 56000, Dipartimento.SALES));

        List<Dipendente> listaDipendenti = dipendenti.stream().collect(Collectors.toList());
        listaDipendenti.sort(Comparator.comparing(Dipendente::getNome));
        //System.out.println(listaDipendenti);

        List<Dipendente> filtroStipendioSopra50 = filtra(dipendenti, d -> d.getStipendio() > 50000);
        List<Dipendente> filtroEtaSopra30 = filtra(dipendenti, d -> d.getEta() > 30);
        System.out.println("Dipendenti con stipendio oltre i 50k: "+filtroStipendioSopra50);
        System.out.println("Dipendenti over 30: "+filtroEtaSopra30);

        System.out.println("Dipendenti piu pagati :");
        List <Dipendente> filtroStipendio = dipendenti.stream().filter(dipendente -> dipendente.getStipendio() > 50000).collect(Collectors.toList());
        filtroStipendio.forEach(System.out::println);

        System.out.println("Dipendenti filtrati per eta crescente :");
        List <Dipendente> filtroEta = dipendenti.stream().sorted(Comparator.comparing(Dipendente::getEta)).collect(Collectors.toList());
        filtroEta.forEach(System.out::println);

        Double filtroMediaStipendio = dipendenti.stream().mapToDouble(Dipendente::getStipendio).average().orElse(0.0);
        System.out.println("Media degli stipendi dei dipendenti: " + filtroMediaStipendio);

        Map<Dipartimento, List<Dipendente>> filtroDipartimento = dipendenti.stream().collect(Collectors.groupingBy(Dipendente::getDipartimento));
        filtroDipartimento.forEach ((dipartimento, lista)
                -> { System.out.println("DIPARTIMENTI : " + dipartimento);
            lista.forEach(d -> System.out.println(d.getNome()));
        });





    }
        public static List<Dipendente> filtra (List < Dipendente > lista, FiltroDipendente requisiti){
            List<Dipendente> dipendentiFiltrati = new ArrayList<>();
            for (Dipendente d : lista) {
                if (requisiti.filtro(d)) {
                    dipendentiFiltrati.add(d);

                }

            }
            return dipendentiFiltrati;

        }

    }