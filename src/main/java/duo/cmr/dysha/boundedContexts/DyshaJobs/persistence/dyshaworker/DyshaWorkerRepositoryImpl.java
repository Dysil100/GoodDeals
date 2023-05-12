package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshaworker;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.workerjobrelation.WorkerJobRelation;
import duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob.DyshaJobRepositoryImpl;
import duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.photo.DyshaFileRepositoryImpl;
import duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.workerjobrelation.WorkerJobRelationRepositoryImpl;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaWorkerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class DyshaWorkerRepositoryImpl implements DyshaWorkerRepository {

    DaoDyshaWorkerRepository daoDyshaWorkerRepository;
    WorkerJobRelationRepositoryImpl workerJobRelationRepository;
    DyshaJobRepositoryImpl dyshaJobRepository;
    DyshaFileRepositoryImpl dyshaPhotoRepository;

    @Override
    public List<DyshaWorker> findAllWorker() {
        return toWorkerList(daoDyshaWorkerRepository.findAll());
    }

    @Override
    public void save(DyshaWorker worker) {
        daoDyshaWorkerRepository.save(toDyshaWorkerEntity(worker));
    }

    @Override
    public DyshaWorker findById(Long id) {
        return !daoDyshaWorkerRepository.existsById(id) ? null : toWorker(daoDyshaWorkerRepository.findById(id).get());
    }

    @Override
    public DyshaWorker findByUserId(Long userId) {
        return daoDyshaWorkerRepository.existsByUserId(userId) ? toWorker(daoDyshaWorkerRepository.findByUserId(userId)) : null;
    }

    @Override
    public boolean existByuserId(Long userId) {
        return daoDyshaWorkerRepository.existsByUserId(userId);
    }

    private DyshaWorkerEntity toDyshaWorkerEntity(DyshaWorker dw) {
        return new DyshaWorkerEntity(dw.getName(), dw.getDescription(), dw.getLocalisation(), dw.getStartedOn(), dw.getUserId());
    }

    private List<DyshaWorker> toWorkerList(Iterable<DyshaWorkerEntity> all) {
        List<DyshaWorker> result = new ArrayList<>();
        all.forEach(e -> result.add(toWorker(e)));
        return result;
    }

    private DyshaWorker  toWorker(DyshaWorkerEntity e) {
        List<DyshaJob> allByWorkerId = dyshaJobRepository.findAllById(workerJobRelationRepository.findAllByWorkerId(e.getUserId()).stream().map(WorkerJobRelation::getJobId).toList());
        String encodedPhoto = dyshaPhotoRepository.findLastByTableNameAndUserIdAndEntityIdAndFileType("dyshaworker", e.getUserId(), e.getId(), "image");
        return new DyshaWorker(e.getId(), e.getName(), allByWorkerId, e.getDescription(), e.getLocation(), e.getStartedOn(), e.getUserId(), encodedPhoto);
    }
}
