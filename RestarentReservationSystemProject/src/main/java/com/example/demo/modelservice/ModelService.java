package com.example.demo.modelservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.example.demo.modelproject.ModelSeat;
import com.example.demo.modelrepository.ModelRepository;

@Service
public class ModelService {
	@Autowired
	ModelRepository mr;
	
	
	public ModelSeat postDetails(ModelSeat ms) {
		return mr.save(ms);
	}
	
	public List<ModelSeat> postnDetails(List<ModelSeat> ms){
		return (List<ModelSeat>)mr.saveAll(ms);
	}
	
	public ModelSeat putDetails(ModelSeat ms) {
		return mr.saveAndFlush(ms);
	}
	public Optional<ModelSeat> updateModel(int id, ModelSeat updatedModel) {
        
        Optional<ModelSeat> existingModel = mr.findById(id);
        if (existingModel.isPresent()) {
            ModelSeat modelToUpdate = existingModel.get();
            // Update the fields you want to change
            modelToUpdate.setName(updatedModel.getName());
            modelToUpdate.setEmail(updatedModel.getEmail());
            modelToUpdate.setMobilenumber(updatedModel.getMobilenumber());
            modelToUpdate.setMembers(updatedModel.getMembers());
            modelToUpdate.setFoodtype(updatedModel.getFoodtype());
            
            return Optional.of(mr.save(modelToUpdate));
        } else {
            
            return Optional.empty();
        }
    }
	
	
	public String changeinfobyid(int id,ModelSeat ms) {
		if(mr.existsById(id)) {
			mr.saveAndFlush(ms);
			return "updated";
		}
		return "invalid";
	}
	
	public void deleteDetails(ModelSeat ms) {
		 mr.delete(ms);
	}
	
	public void deletebyId(int id) {
		mr.deleteById(id);
	}
	public List<ModelSeat> getDetails(){
		return mr.findAll();
	}
	
			public List<ModelSeat> sortinginfo(String s){
				return mr.findAll(Sort.by(Sort.DEFAULT_DIRECTION,s));
			}
	
			public List<ModelSeat> getbypage(int pagno,int pgsize){
				Page<ModelSeat> p=mr.findAll(PageRequest.of(pagno, pgsize));
				return p.getContent();
			}
			
			public List<ModelSeat>getinfos(int s){
				return mr.getstudentinfor(s);
			}
			
		     public int DeleteByQuery(int bookingid){
		    	 return mr.deleteBookById(bookingid);
		     }
		    
		     public int UpdateByQuery(int newid,int oldid) {
		    	 return mr.updateBookById(newid,oldid);
		     }
			
			
}
