package io.github.meucafofo.meucafofo_servico.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import io.github.meucafofo.meucafofo_servico.exception.ServerErrorException;
import io.github.meucafofo.meucafofo_servico.interfaces.ConstraintDTOInterface;

@Component
public class GenericModelMapper<DTO extends ConstraintDTOInterface> {

	private static final ModelMapper MODEL_MAPPER = new ModelMapper();

	public <E> E toEntity(DTO dto, Class<E> cls) {
		try {
			return MODEL_MAPPER.map(dto, cls);
		} catch (IllegalArgumentException | SecurityException e) {
			throw new ServerErrorException("Error ao converter o DTO", e);
		}
	}
}
