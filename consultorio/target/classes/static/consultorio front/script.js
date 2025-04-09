document.getElementById('consulta-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const dataHora = document.getElementById('dataHora').value;
    const motivo = document.getElementById('motivo').value;
    const medicoId = document.getElementById('medicoId').value;
    const pacienteId = document.getElementById('pacienteId').value;
    const retorno = document.getElementById('retorno').value;
    const atestado = document.getElementById('atestado').value;

    const consultaDTO = {
      dataHora: dataHora,
      motivo: motivo,
      medicoId: parseInt(medicoId),
      pacienteId: parseInt(pacienteId),
      retorno: retorno === 'true',
      atestado: atestado
    };

    try {
      const response = await fetch('/consultas', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(consultaDTO)
      });
      if (!response.ok) {
        throw new Error('Erro ao agendar consulta');
      }
      alert('Consulta agendada com sucesso!');
      document.getElementById('consulta-form').reset();
    } catch (error) {
      alert(error);
    }
});

document.getElementById('listarConsultas').addEventListener('click', async function() {
  try {
    const response = await fetch('/consultas');
    if (!response.ok) {
      throw new Error('Erro ao obter consultas');
    }
    const consultas = await response.json();
    const tbody = document.querySelector('#consultas-table tbody');
    tbody.innerHTML = ''; // Limpa as linhas anteriores

    consultas.forEach(consulta => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td>${consulta.id}</td>
        <td>${new Date(consulta.dataHora).toLocaleString()}</td>
        <td>${consulta.motivo}</td>
        <td>${consulta.nomeMedico}</td>
        <td>${consulta.nomePaciente}</td>
        <td>${consulta.retorno ? 'Sim' : 'Não'}</td>
        <td>${consulta.atestado ? consulta.atestado : ''}</td>
      `;
      tbody.appendChild(tr);
    });
  } catch (error) {
    alert(error);
  }
});
