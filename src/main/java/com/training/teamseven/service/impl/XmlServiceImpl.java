package com.training.teamseven.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.teamseven.controller.dto.EmployeeDTO;
import com.training.teamseven.entity.Employee;
import com.training.teamseven.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class XmlServiceImpl implements XmlService {
    private static final String Topic = "kafka_example";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void xmlParser() {
        try {
            File file = new File("/Users/prateekkoul/Downloads/team7-master/src/main/java/com/training/teamseven/files/employee.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("employee");//nodelist give ordered list of nodes
            for (int itr = 0; itr < nodeList.getLength(); itr++) {//getlength gives no of nodes
                Node node = nodeList.item(itr);//item returns the node at specified index
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    EmployeeDTO employee=new EmployeeDTO();
                    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse((eElement.getElementsByTagName("dateOfBirth").item(0).getTextContent()));
                    employee.setDateOfBirth(date1);
                    employee.setExperience(Double.parseDouble(eElement.getElementsByTagName("experience").item(0).getTextContent()));
                    employee.setFirstName(eElement.getElementsByTagName("firstName").item(0).getTextContent());
                    employee.setLastName(eElement.getElementsByTagName("lastName").item(0).getTextContent());
                    System.out.println("XML");
                    ObjectMapper objectMapper = new ObjectMapper();
                    kafkaTemplate.send(Topic,objectMapper.writeValueAsString(employee));

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
