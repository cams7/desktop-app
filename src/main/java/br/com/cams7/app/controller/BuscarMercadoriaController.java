package br.com.cams7.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cams7.app.action.AbstractAction;
import br.com.cams7.app.dao.MercadoriaDAO;
import br.com.cams7.app.event.BuscarMercadoriaEvent;
import br.com.cams7.app.model.MercadoriaEntity;
import br.com.cams7.app.ui.BuscaMercadoriaFrame;

/**
 * Define a <code>Controller</code> responsável por gerir a tela de Busca de
 * <code>Mercadoria</code> pelo campo <code>nome</code>.
 * 
 * <p>
 * <code>BuscarMercadoriaController</code> é mapeada como
 * <code>@Component</code> do Spring. Dessa forma uma instância de
 * <code>BuscarMercadoriaController</code> pode ser criada e gerenciada pelo
 * Spring, favorecendo a Inversão de Controle <i>(IoC)</i> e Injeção de
 * Dependência <i>(DI)</i>.
 * </p>
 * 
 * <p>
 * Essa <code>Controller</code> depende de outros componentes da camada
 * <code>Model</code> (DAO) e <code>View</code> (tela). As dependências são
 * resolvidas pelo Spring, através da <strong>Injeção de Dependência</strong> c/
 * a anotação <code>@Autowired</code>.
 * </p>
 * 
 * @see br.com.yaw.sjpac.controller.PersistenceController
 * 
 * @author YaW Tecnologia
 */
@Component
public class BuscarMercadoriaController extends AbstractController {

	@Autowired
	private BuscaMercadoriaFrame frame;

	@Autowired
	private MercadoriaDAO dao;

	@Autowired
	public BuscarMercadoriaController(ListaMercadoriaController parent) {
		super(parent);
	}

	@PostConstruct
	private void init() {
		this.frame.addWindowListener(this);

		registerAction(frame.getBuscarButton(), new AbstractAction() {
			private List<MercadoriaEntity> list;

			@Override
			public void action() {
				if (frame.getText().length() > 0) {
					list = dao.getMercadoriasByNome(frame.getText().concat("%"));
				}
			}

			@Override
			public void posAction() {
				cleanUp();
				fireEvent(new BuscarMercadoriaEvent(list));
				list = null;
			}
		});
	}

	public void show() {
		frame.setVisible(true);
	}

	@Override
	protected void cleanUp() {
		frame.setVisible(false);
		frame.resetForm();

		super.cleanUp();
	}
}
