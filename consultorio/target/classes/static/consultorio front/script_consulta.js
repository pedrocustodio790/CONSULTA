document.addEventListener('DOMContentLoaded', function () {
  // Agendamento da consulta
  document.getElementById('consulta-form').addEventListener('submit', async function (event) {
    event.preventDefault();

    const dataHora = document.getElementById('dataHora').value;
    const motivo = document.getElementById('motivo').value;
    const medicoId = document.getElementById('medicoId').value;
    const pacienteId = document.getElementById('pacienteId').value;
    const retorno = document.getElementById('retorno').value;
    const atestado = document.getElementById('atestado').value;
    const valorConsulta = parseFloat(document.getElementById('valorConsulta').value);

    const consultaDTO = {
      dataHora,
      motivo,
      medicoId: parseInt(medicoId),
      pacienteId: parseInt(pacienteId),
      retorno: retorno === 'true',
      atestado,
      valorConsulta
    };

    try {
      const response = await fetch('http://localhost:8080/consultas', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(consultaDTO)
      });

      if (!response.ok) throw new Error('Erro ao agendar consulta');

      alert('Consulta agendada com sucesso!');
      document.getElementById('consulta-form').reset();
      listarConsultas(); // Atualiza a tabela automaticamente
    } catch (error) {
      alert(error);
    }
  });

  // Função para listar todas as consultas
  async function listarConsultas() {
    try {
      const res = await fetch('http://localhost:8080/consultas');
      if (!res.ok) throw new Error('Erro ao buscar consultas');

      const consultas = await res.json();
      const tbody = document.querySelector('#consultas-table tbody');
      tbody.innerHTML = '';

      consultas.forEach(c => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <td>${c.id}</td>
          <td>${new Date(c.dataHora).toLocaleString()}</td>
          <td>${c.motivo}</td>
          <td>${c.nomeMedico}</td>
          <td>${c.nomePaciente}</td>
          <td>${c.retorno ? 'Sim' : 'Não'}</td>
          <td>${c.atestado || ''}</td>
        `;
        tbody.appendChild(tr);
      });
    } catch (err) {
      console.error(err);
    }
  }

  // Atualizar tabela manualmente com botão
  document.getElementById('listarConsultas').addEventListener('click', listarConsultas);

  // Listar ao carregar a página
  listarConsultas();
});
