package vn.dev.clinics.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.dev.clinics.entity.Message;
import vn.dev.clinics.entity.User;
import vn.dev.clinics.model.MessageModel;

@Service
public class MessageService extends BaseService<Message>{

	@Override
	public Class<Message> clazz() {
		return Message.class;
	}
	
	@Autowired
	private TransmitService transmitService;

	@Autowired
	private UserService userService;
	
	public MessageModel getModelById(Integer id) {
		MessageModel model = transmitService.messageToModel(super.getById(id));
		return model;
	}
	
	public List<MessageModel> findAllModel(){
		List<Message> entities = super.findAll();
		List<MessageModel> messages = transmitService.mesageEntitiesToModels(entities);
		return messages;
	}
	
	@Transactional
	public MessageModel saveMessage(String usernameLogined, MessageModel model, Integer patientId) {
		User userLogined = userService.getUserByUsername(usernameLogined);
		model.setSenderId(userLogined.getId());
		Message entity = transmitService.messsageToEntity(model);
		entity.setCreateDate(new Date());
		entity.setReceiverId(patientId);
		super.saveOrUpdate(entity);
		return model;
	}
	
	@Transactional
	public void deleteById(Integer id) {
		super.deleteById(id);
	}
	
	@Transactional
	public MessageModel updatePatient(MessageModel model, Integer id) {
		Message entity = super.getById(id);
		entity.setUpdateDate(new Date());
		entity.setContent(model.getContent());
		super.saveOrUpdate(entity);
		return model;
	}


	public List<MessageModel> findAllConversation(String usernameLogined, Integer partnerId) {
		User u = userService.getUserByUsername(usernameLogined);
		Integer id = u.getId();
		String sql = "SELECT * FROM clinics04.tbl_message where sender_id="+ id +" and receiver_id="+ partnerId +" or sender_id="+ partnerId +" and receiver_id="+ id +";";
		List<Message> entities = super.executeNativeSql(sql);
		List<MessageModel> models = transmitService.mesageEntitiesToModels(entities);
		return models;
	}
}
