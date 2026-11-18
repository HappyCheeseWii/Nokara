
package com.nokara.ejb;

import com.nokara.models.Case;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CaseService {

    @PersistenceContext(unitName = "NokaraPU")
    private EntityManager em;

    public boolean registrar(Case caso) {

        if (caso == null ||
                caso.getTitle() == null ||
                caso.getTitle().trim().isEmpty()) {
            return false;
        }

        if (caso.getDocuments() != null) {
            for (Case.DocumentInfo documento : caso.getDocuments()) {
                documento.setCaseItem(caso);
            }
        }

        em.persist(caso);
        return true;
    }

    public List<Case> listarTodos() {

        return em.createQuery(
                "SELECT DISTINCT c FROM Case c " +
                        "LEFT JOIN FETCH c.documents " +
                        "ORDER BY c.createdAt DESC",
                Case.class
        ).getResultList();
    }

    public Case buscarPorId(Long id) {

        if (id == null) {
            return null;
        }

        List<Case> resultados = em.createQuery(
                "SELECT DISTINCT c FROM Case c " +
                        "LEFT JOIN FETCH c.documents " +
                        "WHERE c.id = :id",
                Case.class
        ).setParameter("id", id).getResultList();

        return resultados.isEmpty() ? null : resultados.get(0);
    }

    public boolean actualizar(Case caso) {

        if (caso == null ||
                caso.getId() == null ||
                caso.getTitle() == null ||
                caso.getTitle().trim().isEmpty()) {
            return false;
        }

        Case existente = em.find(Case.class, caso.getId());

        if (existente == null) {
            return false;
        }

        existente.setTitle(caso.getTitle().trim());
        existente.setDescription(caso.getDescription());

        return true;
    }

    public boolean eliminar(Long id) {

        if (id == null) {
            return false;
        }

        Case caso = em.find(Case.class, id);

        if (caso == null) {
            return false;
        }

        em.remove(caso);
        return true;
    }

    public long contarCasos() {

        return em.createQuery(
                "SELECT COUNT(c) FROM Case c",
                Long.class
        ).getSingleResult();
    }

    public boolean agregarDocumento(
            Long caseId,
            Case.DocumentInfo documento) {

        if (caseId == null ||
                documento == null ||
                documento.getOriginalFileName() == null ||
                documento.getCustomTitle() == null ||
                documento.getCustomTitle().trim().isEmpty() ||
                documento.getFilePath() == null) {
            return false;
        }

        Case caso = em.find(Case.class, caseId);

        if (caso == null) {
            return false;
        }

        documento.setCaseItem(caso);
        caso.getDocuments().add(documento);

        return true;
    }

    public boolean eliminarDocumento(Long documentId) {

        if (documentId == null) {
            return false;
        }

        Case.DocumentInfo documento = em.find(
                Case.DocumentInfo.class,
                documentId
        );

        if (documento == null) {
            return false;
        }

        Case caso = documento.getCaseItem();
        caso.getDocuments().remove(documento);

        return true;
    }
}
