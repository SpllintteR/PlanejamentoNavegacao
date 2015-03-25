package dev;

import java.util.ArrayList;
import java.util.List;

public class MapaTrapezoidal {

	private int[][] pontos; 
	
	public static void main(String[] args) {
		MapaTrapezoidal mapa = new MapaTrapezoidal();
		Cenario cenario = new Cenario();
		cenario.setLinhas(7);
		cenario.setColunas(6);
		cenario.setInicio(new Ponto(2, 5));
		cenario.setFim(new Ponto(6, 0));
		cenario.addPontoInvalido(new Ponto(0, 1));
		cenario.addPontoInvalido(new Ponto(1, 3));
		cenario.addPontoInvalido(new Ponto(2, 4));
		cenario.addPontoInvalido(new Ponto(3, 2));
		cenario.addPontoInvalido(new Ponto(4, 4));
		cenario.addPontoInvalido(new Ponto(5, 0));
		cenario.addPontoInvalido(new Ponto(5, 2));
		cenario.addPontoInvalido(new Ponto(6, 3));
		cenario.addPontoInvalido(new Ponto(6, 5));
		mapa.addCenario(cenario);
		for(int i = 0; i < mapa.pontos.length; i++){
			for(int j = 0; j < mapa.pontos[i].length; j++){
				System.out.println(mapa.pontos[i][j] + " ");
			}	
		}
		 //move robo
	}

	private void addCenario(Cenario cenario) {
		pontos = new int[cenario.getLinhas()][cenario.getColunas()];
		for(Ponto p: cenario.getPontosInvalidos()){
			pontos[p.getX()][p.getY()] = -1;
		}
		Ponto p = cenario.getFim();
		pontos[p.getX()][p.getY()] = 2;
		
//		p = cenario.getInicio();
//		pontos[p.getX()][p.getY()] = 1;
		
		List<Ponto> ps = new ArrayList<Ponto>();
		preencheVizinhos(ps, p);
		while(ps.size() > 0){
			preencheVizinhos(ps, ps.get(0));
			ps.remove(0);
		}
	}
	
	private void preencheVizinhos(List<Ponto> ps, Ponto p){
		int value = pontos[p.getX()][p.getY()] + 1;
		if(p.getX() > 0){
			Ponto x = new Ponto(p.getX() - 1, p.getY());
			ps.add(x);
			pontos[x.getX()][x.getY()] = value;
		}
		if(p.getY() > 0){
			Ponto x = new Ponto(p.getX(), p.getY() - 1);
			ps.add(x);
			pontos[x.getX()][x.getY()] = value;
		}
		if(p.getX() < (pontos.length - 1)){
			Ponto x = new Ponto(p.getX() + 1, p.getY());
			ps.add(x);
			pontos[x.getX()][x.getY()] = value;
		}
		if(p.getY() < (pontos[p.getX()].length - 1)){
			Ponto x = new Ponto(p.getX(), p.getY() + 1);
			ps.add(x);
			pontos[x.getX()][x.getY()] = value;
		}	
	}
	
	/*
	 * 0 - 0 0 0 0
	 * 0 0 0 - 0 0
	 * 0 0 0 0 - 1
	 * 0 0 - 0 0 0
	 * 0 0 0 0 - 0
	 * - 0 - 0 0 0
	 * 2 0 0 - 0 -
	 * 
	 * 
	 * 0 0 0 0 0 0
	 * 0 0 - 0 0 0
	 * 0 0 0 - 0 0
	 * 1 - 0 0 - 0
	 * 0 0 - 0 0 0
	 * 0 0 0 0 - 0
	 * 0 0 0 - 0 1
	 * 
	 */
}
