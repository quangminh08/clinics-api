package vn.dev.clinics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.dev.clinics.model.MessageModel;
import vn.dev.clinics.service.MessageService;


@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	
	@GetMapping("/{id}")
	public MessageModel message(@PathVariable("id") Integer id) {
		return messageService.getModelById(id);
	}
	
	@GetMapping("/conversation/{partnerId}")
	public List<MessageModel> messages(HttpServletRequest request ,@PathVariable("partnerId") Integer partnerId){
		String usernameLogined =request.getUserPrincipal().getName();
		return messageService.findAllConversation(usernameLogined, partnerId);
	}
	
	@PostMapping("conversation/{patientId}")
	public MessageModel savePatientMessage(HttpServletRequest request, @RequestBody MessageModel message, @PathVariable("patientId") Integer patientId) {
		String usernameLogined =request.getUserPrincipal().getName();
		return messageService.saveMessage(usernameLogined, message, patientId);
	}

	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Integer id) {
		messageService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public MessageModel update(@RequestBody MessageModel model, @PathVariable("id") Integer id) {
		return messageService.updatePatient(model, id);
	}
	
}
