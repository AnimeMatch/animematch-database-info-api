package animatch.app.service.usuario.mapper;

import animatch.app.domain.usuario.Usuario;
import animatch.app.dto.UsuarioImportacaoDTO;

public class UsuarioRelatorioMapper extends Mapper<Usuario, UsuarioImportacaoDTO>{

    @Override
    protected UsuarioImportacaoDTO converterEntidadeParaDto(Usuario entidade) {
        return new UsuarioImportacaoDTO(entidade);
    }

    @Override
    protected Usuario converterDtoParaEntidade(UsuarioImportacaoDTO dto) {
        return null;
    }
}
