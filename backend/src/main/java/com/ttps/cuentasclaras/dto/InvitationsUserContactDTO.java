package com.ttps.cuentasclaras.dto;

import java.util.List;

public class InvitationsUserContactDTO {

    private List<InvitationUserContactDTO> solicitudesEnviadas;

    private List<InvitationUserContactDTO> solicitudesRecibidas;

    public InvitationsUserContactDTO() {
        super();
    }

    public InvitationsUserContactDTO(List<InvitationUserContactDTO> solicitudesEnviadas,
                                     List<InvitationUserContactDTO> solicitudesRecibidas) {
        this.solicitudesEnviadas = solicitudesEnviadas;
        this.solicitudesRecibidas = solicitudesRecibidas;
    }

    public List<InvitationUserContactDTO> getSolicitudesEnviadas() {
        return solicitudesEnviadas;
    }

    public void setSolicitudesEnviadas(List<InvitationUserContactDTO> solicitudesEnviadas) {
        this.solicitudesEnviadas = solicitudesEnviadas;
    }

    public List<InvitationUserContactDTO> getSolicitudesRecibidas() {
        return solicitudesRecibidas;
    }

    public void setInvitacionesRecibidas(List<InvitationUserContactDTO> solicitudesRecibidas) {
        this.solicitudesRecibidas = solicitudesRecibidas;
    }
}
