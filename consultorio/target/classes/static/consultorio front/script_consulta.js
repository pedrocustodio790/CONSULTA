const API_BASE = 'http://localhost:8080';

document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('consulta-form');
  const listarBtn = document.getElementById('listarConsultas');

  form.addEventListener('submit', async event => {
    event.preventDefault();

    const consulta = {
      dataHora: form.dataHora.value,
      motivo: form.motivo.value.trim(),
      medicoId: parseInt(form.medicoId.value),
      pacienteId: parseInt(form.pacienteId.value),
      retorno: form.retorno.value === 'true',
      atestado: form.atestado.value.trim()
    };
    console.log('Enviando consulta:', consulta);

    try {
      const response = await fetch(`${API_BASE}/consultas`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(consulta)
      });

      if (!response.ok) throw new Error('Erro ao agendar consulta');

      alert('Consulta agendada com sucesso!');
      form.reset();
      carregarConsultas();
    } catch (error) {
      alert(error.message);
    }
  });

  listarBtn.addEventListener('click', carregarConsultas);
  carregarConsultas();
});

async function carregarConsultas() {
  try {
    const response = await fetch(`${API_BASE}/consultas`);
    if (!response.ok) throw new Error('Erro ao obter consultas');

    const consultas = await response.json();
    const tbody = document.querySelector('#consultas-table tbody');
    tbody.innerHTML = '';

    consultas.forEach(c => {
      const tr = document.createElement('tr');
      tr.appendChild(criarTd(c.id));
      tr.appendChild(criarTd(new Date(c.dataHora).toLocaleString()));
      tr.appendChild(criarTd(c.motivo));
      tr.appendChild(criarTd(c.nomeMedico));
      tr.appendChild(criarTd(c.nomePaciente));
      tr.appendChild(criarTd(c.retorno ? 'Sim' : 'Não'));
      tr.appendChild(criarTd(c.atestado || ''));
      tbody.appendChild(tr);
    });
  } catch (error) {
    alert(error.message);
  }
}

function criarTd(valor) {
  const td = document.createElement('td');
  td.textContent = valor;
  return td;
}
