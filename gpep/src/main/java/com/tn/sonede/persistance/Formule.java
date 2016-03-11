package com.tn.sonede.persistance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.mvel2.MVEL;
import org.mvel2.compiler.ExecutableStatement;


/**
 * Personnel generated by hbm2java
 */
@Entity
@Table(name = "Formule", catalog = "public")
public class Formule implements java.io.Serializable {

	private Integer id;
	private String code;
	private String designation;
	private String expression;
	private String type;
	private TypeRessource typeres;
	private Object resultat;
	private Boolean OK;

	public Formule() {
	}

	public Formule(Integer id) {
		this.id = id;
	}

	public Formule(Integer id,String code, String designation, String expression,String  type,TypeRessource typeres) {
		this.id = id;
		this.code = code;
		this.designation = designation;
		this.expression = expression;
		this.type = type;
		this.typeres= typeres;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	@Column(name = "code", unique = true, nullable = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	@Column(name = "designation", length = 254)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name = "expression")
	public String getExpression() {
		return this.expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	@Column(name = "type_rapport")
	public String  getType() {
		return this.type;
	}

	public void setType(String  type) {
		this.type = type;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_type",nullable = false)
	public TypeRessource getTypeRessource() {
		return this.typeres;
	}

	public void setTypeRessource(TypeRessource typeres) {
		this.typeres = typeres;
	}

	
	@Transient
	public Object getResultat() {
		return resultat;
	}

	public void setResultat(Object resultat) {
		this.resultat = resultat;
	}
	
	@Transient
	public Boolean getOK() {
		return OK;
	}

	public void setOK(Boolean oK) {
		OK = oK;
	}
	
	public void eval(List<Caracterestique> listCaracterestiques) {
		if(formuleValide(listCaracterestiques)) {
			OK=true;
		} else {
			OK=false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Formule other = (Formule) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private boolean formuleValide(List<Caracterestique> listCaracterestiques){
		String expressionStr = getExpression();
	    Map<String, Object> vars = new HashMap<String, Object>();
	    for(Caracterestique c:listCaracterestiques){
	    	if(c.getType()==0)
	    		vars.put(c.getDesignation(), Double.parseDouble(c.getNewValeur()));
	    }
	    try{
	    	ExecutableStatement statement = (ExecutableStatement) MVEL.compileExpression(expressionStr);
	    	resultat =  MVEL.executeExpression(statement, vars);
	    } catch(Exception e){
	    	return false;
	    }
	    return true;
	}
	

}