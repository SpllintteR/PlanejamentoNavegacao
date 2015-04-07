package dev;

import java.util.ArrayList;
import java.util.List;

public class WaveFront {

	private int[][]	pontos;
	private Ponto	inicio;
	private Ponto	fim;

	public static void main(final String[] args) {
		WaveFront mapa = new WaveFront();
		Cenario cenario = new Cenario();
		cenario.setLinhas(7);
		cenario.setColunas(6);
		cenario.setInicio(new Ponto(6, 0));
		cenario.setFim(new Ponto(2, 5));
		cenario.addPontoInvalido(new Ponto(0, 1));
		cenario.addPontoInvalido(new Ponto(1, 3));
		cenario.addPontoInvalido(new Ponto(2, 4));
		cenario.addPontoInvalido(new Ponto(3, 2));
		cenario.addPontoInvalido(new Ponto(4, 4));
		cenario.addPontoInvalido(new Ponto(5, 0));
		cenario.addPontoInvalido(new Ponto(5, 2));
		cenario.addPontoInvalido(new Ponto(6, 3));
		cenario.addPontoInvalido(new Ponto(6, 5));
		mapa.valorarGrafo(cenario);

		for (int i = 0; i < mapa.pontos.length; i++) {
			String str = "";
			for (int j = 0; j < mapa.pontos[i].length; j++) {
				str += mapa.pontos[i][j] + " ";
			}
			System.out.println(str);
		}

		List<Passo> passos = new ArrayList<>();
		mapa.encontrarPassos(passos, mapa.inicio);
		while (passos.size() > 0) {
			Passo p = passos.get(0);
			System.out.println(p);
			if (p == Passo.DIREITA) {

			} else {
				if (p == Passo.ESQUERDA) {

				} else {
					if (p == Passo.CIMA) {

					} else {

					}
				}
			}
			passos.remove(0);
		}

	}

	private void encontrarPassos(final List<Passo> passos, final Ponto ponto) {
		if (pontos[ponto.getX()][ponto.getY()] != 2) {
			Ponto prox = null;
			int melhor = Integer.MAX_VALUE;
			Passo ret = null;
			int aux = 0;
			if (ponto.getY() != (pontos[0].length - 1)) {
				if (pontos[ponto.getX()][ponto.getY() + 1] != -1) {
					prox = new Ponto(ponto.getX(), ponto.getY() + 1);
					melhor = pontos[prox.getX()][prox.getY()];
					ret = Passo.DIREITA;
				}
			}

			if (ponto.getY() > 0) {
				aux = pontos[ponto.getX()][ponto.getY() - 1];
				if ((aux != -1) && (aux < melhor)) {
					melhor = aux;
					ret = Passo.ESQUERDA;
					prox = new Ponto(ponto.getX(), ponto.getY() - 1);
				}
			}

			if (ponto.getX() != (pontos.length - 1)) {
				aux = pontos[ponto.getX() + 1][ponto.getY()];
				if ((aux != -1) && (aux < melhor)) {
					melhor = aux;
					ret = Passo.BAIXO;
					prox = new Ponto(ponto.getX() + 1, ponto.getY());
				}
			}

			if (ponto.getX() > 0) {
				aux = pontos[ponto.getX() - 1][ponto.getY()];
				if ((aux != -1) && (aux < melhor)) {
					ret = Passo.CIMA;
					prox = new Ponto(ponto.getX() - 1, ponto.getY());
				}
			}
			passos.add(ret);
			System.out.println(ret);
			encontrarPassos(passos, prox);
		}
	}

	private void valorarGrafo(final Cenario cenario) {
		pontos = new int[cenario.getLinhas()][cenario.getColunas()];
		for (Ponto p : cenario.getPontosInvalidos()) {
			pontos[p.getX()][p.getY()] = -1;
		}
		fim = cenario.getFim();
		inicio = cenario.getInicio();

		pontos[fim.getX()][fim.getY()] = 2;

		List<Ponto> ps = new ArrayList<Ponto>();
		preencheVizinhos(ps, fim);
		while (ps.size() > 0) {
			preencheVizinhos(ps, ps.get(0));
			ps.remove(0);
		}
	}

	private void preencheVizinhos(final List<Ponto> ps, final Ponto p) {
		int value = pontos[p.getX()][p.getY()] + 1;
		if (p.getX() > 0) {
			Ponto x = new Ponto(p.getX() - 1, p.getY());
			if (pontos[x.getX()][x.getY()] == 0) {
				ps.add(x);
				pontos[x.getX()][x.getY()] = value;
			}
		}
		if (p.getY() > 0) {
			Ponto x = new Ponto(p.getX(), p.getY() - 1);
			if (pontos[x.getX()][x.getY()] == 0) {
				ps.add(x);
				pontos[x.getX()][x.getY()] = value;
			}
		}
		if (p.getX() < (pontos.length - 1)) {
			Ponto x = new Ponto(p.getX() + 1, p.getY());
			if (pontos[x.getX()][x.getY()] == 0) {
				ps.add(x);
				pontos[x.getX()][x.getY()] = value;
			}
		}
		if (p.getY() < (pontos[p.getX()].length - 1)) {
			Ponto x = new Ponto(p.getX(), p.getY() + 1);
			if (pontos[x.getX()][x.getY()] == 0) {
				ps.add(x);
				pontos[x.getX()][x.getY()] = value;
			}
		}
	}

	/*
	 * 0 - 0 0 0 0 0 0 0 - 0 0 0 0 0 0 - 1 0 0 - 0 0 0 0 0 0 0 - 0 - 0 - 0 0 0 2
	 * 0 0 - 0 -
	 * 
	 * 
	 * 0 0 0 0 0 0 0 0 - 0 0 0 0 0 0 - 0 0 1 - 0 0 - 0 0 0 - 0 0 0 0 0 0 0 - 0 0
	 * 0 0 - 0 1
	 * 
	 * 
	 * 
	 * 10 -1 10 11 12 13 9 8 9 -1 13 12 8 7 8 9 -1 11 7 6 -1 8 9 10 6 5 6 7 -1
	 * 11 -1 4 -1 8 9 10 2 3 4 -1 10 -1
	 */
}
