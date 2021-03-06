package br.com.cams7.app.controller;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.app.action.AbstractAction;
import br.com.cams7.app.action.BooleanExpression;
import br.com.cams7.app.action.ConditionalAction;
import br.com.cams7.app.dao.MercadoriaDAO;
import br.com.cams7.app.event.DeletarMercadoriaEvent;
import br.com.cams7.app.event.IncluirMercadoriaEvent;
import br.com.cams7.app.model.MercadoriaEntity;
import br.com.cams7.app.ui.IncluirMercadoriaFrame;
import br.com.cams7.app.validation.EntityValidator;

/**
 * Define a <code>Controller</code> responsável por gerir a tela de
 * inclusão/edição de <code>Mercadoria</code>.
 * 
 * <p>
 * <code>IncluirMercadoriaController</code> é mapeada como
 * <code>@Component</code> do Spring. Dessa forma uma instância de
 * <code>IncluirMercadoriaController</code> pode ser criada e gerenciada pelo
 * Spring, favorecendo a Inversão de Controle <i>(IoC)</i> e Injeção de
 * Dependência <i>(DI)</i>.
 * </p>
 * 
 * <p>
 * Essa <code>Controller</code> depende de outros componentes da camada
 * <code>Model</code> (DAO) e <code>View</code> (telas). As dependências são
 * resolvidas pelo Spring, através da <strong>Injeção de Dependência</strong> c/
 * a anotação <code>@Autowired</code>.
 * </p>
 * 
 * @see br.com.yaw.sjpac.controller.PersistenceController
 * 
 * @author YaW Tecnologia
 */
@Component
public class IncluirMercadoriaController extends AbstractController {

	@Autowired
	private IncluirMercadoriaFrame frame;

	@Autowired
	private EntityValidator<MercadoriaEntity> validador;

	@Autowired
	private MercadoriaDAO dao;

	@Autowired
	public IncluirMercadoriaController(ListaMercadoriaController parent) {
		super(parent);
	}

	/**
	 * Método executado pelo <code>Spring</code>, depois de criar a instância de
	 * <code>IncluirMercadoriaController</code>.
	 * 
	 * <p>
	 * Faz o registro das ações.
	 * </p>
	 */
	@PostConstruct
	private void init() {
		frame.addWindowListener(this);
		registerAction(frame.getCancelarButton(), new AbstractAction() {
			public void action() {
				cleanUp();
			}
		});

		registerAction(frame.getSalvarButton(), ConditionalAction.build().addConditional(new BooleanExpression() {

			@Override
			public boolean conditional() {
				MercadoriaEntity m = frame.getMercadoria();
				String msg = validador.validate(m);
				if (!"".equals(msg == null ? "" : msg)) {
					JOptionPane.showMessageDialog(frame, msg, "Validação", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
				return true;
			}
		}).addAction(new AbstractAction() {
			private MercadoriaEntity m;

			@Transactional
			@Override
			protected void action() {
				m = frame.getMercadoria();
				dao.save(m);
			}

			@Override
			public void posAction() {
				cleanUp();
				fireEvent(new IncluirMercadoriaEvent(m));
				m = null;
			}
		}));

		registerAction(frame.getExcluirButton(), new AbstractAction() {
			private MercadoriaEntity m;

			@Transactional
			@Override
			protected void action() {
				m = frame.getMercadoria();
				Integer id = frame.getMercadoriaId();
				if (id != null) {
					dao.delete(id);
				}
			}

			public void posAction() {
				cleanUp();
				fireEvent(new DeletarMercadoriaEvent(m));
				m = null;
			}
		});
	}

	public void show() {
		frame.setTitle("Incluir Mercadoria");
		frame.setVisible(true);
	}

	public void show(MercadoriaEntity m) {
		frame.setMercadoria(m);
		frame.setTitle("Editar Mercadoria");
		frame.setVisible(true);
	}

	@Override
	protected void cleanUp() {
		frame.setVisible(false);
		frame.resetForm();

		super.cleanUp();
	}

}
