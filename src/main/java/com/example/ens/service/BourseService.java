package com.example.ens.service;

import com.example.ens.dto.*;
import com.example.ens.dto.req.BourseReq;
import com.example.ens.dto.req.DepenceReq;
import com.example.ens.entities.Bourse;
import com.example.ens.entities.Depence;
import com.example.ens.entities.Source;
import com.example.ens.entities.TypeDepence;
import com.example.ens.exception.BourseException;
import com.example.ens.exception.DepenceException;
import com.example.ens.exception.SourceException;
import com.example.ens.exception.TypeDepenceException;
import com.example.ens.mapper.IBourseMapper;
import com.example.ens.reposetory.BourseRepo;
import com.example.ens.reposetory.DepenceRepo;
import com.example.ens.reposetory.SourceRepo;
import com.example.ens.reposetory.TypeDepenceRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@ToString
@Transactional // pour garantir que les requette sql passe en mode transactionel begin commit ebn cas ok else roolback en cas ko
public class BourseService implements IBourceService{

    BourseRepo bourseRepo;
    IBourseMapper bourseMapper;
    @Override
    public BourseDTO saveBourse(BourseReq req) throws SourceException {
        // bourseReq
        // tester sur source
        //Bourse save = bourseRepo.save(bourseMapper.fromBourseDTO(bourseDTO));
        //return bourseMapper.fromBourse(save);
        System.out.println(req.toString());
        Optional<Source> source = sourceRepo.findById(req.getSourceId());
        if(source.isPresent())
        {
            BourseDTO bourseDTO=new BourseDTO();
            bourseDTO.setDateBourse(req.getDateBourse());
            bourseDTO.setMontantBourse(req.getMontantBourse());
            bourseDTO.setRefBourse(req.getRefBourse());
            bourseDTO.setSource(bourseMapper.fromSource(source.get()));
            Bourse save = bourseRepo.save(bourseMapper.fromBourseDTO(bourseDTO));
            return bourseMapper.fromBourse(save);
        }
        else
        {
            throw new SourceException("source not found");
        }
    }

    @Override
    public BourseDTO updateBourse(BourseReq req) throws BourseException, SourceException {
        Bourse bourse = bourseRepo.findById(req.getId()).orElse(null);

        if(bourse==null){
            throw new BourseException("Bourse not found");
        }else{
            Optional<Source> source = sourceRepo.findById(req.getSourceId());
            if(source.isPresent()){
                BourseDTO bourseDTO=new BourseDTO();
                bourseDTO.setId(req.getId());
                bourseDTO.setDateBourse(req.getDateBourse());
                bourseDTO.setMontantBourse(req.getMontantBourse());
                bourseDTO.setRefBourse(req.getRefBourse());
                bourseDTO.setSource(bourseMapper.fromSource(source.get()));
                Bourse save = bourseRepo.save(bourseMapper.fromBourseDTO(bourseDTO));
                return bourseMapper.fromBourse(save);
            }else {
                throw new SourceException("source not found");
            }
        }



    }

    @Override
    public BourseDTO getBourseById(Long id ) throws BourseException {
        Bourse bourse = bourseRepo.findById(id).orElseThrow(() -> {
            return new BourseException("bourse not found");
        });
        BourseDTO bourseDTO = bourseMapper.fromBourse(bourse);
        return bourseDTO;
    }

    @Override
    public List<BourseDTO> findAllBysource_id(Long id) throws SourceException {
        //tester sur source
        sourceRepo.findById(id).orElseThrow(()-> new SourceException("source not found"));
        List<BourseDTO> collect = bourseRepo.findAllBySource_Id(id).stream().map(data -> {
            return bourseMapper.fromBourse(data);
        }).collect(Collectors.toList());
        return collect;
    }

   /* @Override
    public List<DepenceDTO> findAllByBourse_Id(Long id) throws BourseException {
        bourseRepo.findById(id).orElseThrow(()-> new BourseException("Bourse not found"));
        List<DepenceDTO> collect = depenceRepo.findAllByBourse_Id(id).stream().map(data -> {
            return bourseMapper.fromDepence(data);
        }).collect(Collectors.toList());
        return collect;
    }*/
    /*@Override
    public BourseDTO getAllBourseBySource(Long idSourse) throws BourseException {
        Bourse bourse = (Bourse) bourseRepo.findAllById(Collections.singleton(idSourse));
        BourseDTO bourseDTO = bourseMapper.fromBourse(bourse);
        Source source = (Source) bourseRepo.findAll();
        SourceDTO sourceDTO= bourseMapper.fromSource(source);
        return bourseDTO;
    }*/



    @Override
    public List<BourseDTO> getAllBourse() {
        List<BourseDTO> collect = bourseRepo.findAll().stream().map(data -> {
            return bourseMapper.fromBourse(data);
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Page<Bourse> getAllBourseByPage(int page, int size) {
//        Page<BourseDTO> collect = (Page<BourseDTO>) bourseRepo.findAll(
//                PageRequest.of(page,size)).stream().map(data ->{
//            return bourseMapper.fromBourse(data);
//        }).collect(Collectors.toList());
//        return collect;
//        Page<BourseDTO> collect = (Page<BourseDTO>) bourseRepo.findAll(PageRequest.of(page,size)).stream().map(data ->{
//            return bourseMapper.fromBourse(data);
//        }).collect(Collectors.toList());
        return bourseRepo.findAll(PageRequest.of(page,size));
    }

    @Override
    public void deletBourse(Long id) throws BourseException {
        Bourse bourse = null; try {
            bourseRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("bourse not found");
        }
        BourseDTO bourseDTO = bourseMapper.fromBourse(bourse);

    }

    @Override
    public double sumBourse() throws BourseException {
        return bourseRepo.getSumBourse();
    }

    SourceRepo sourceRepo;

    @Override
    public SourceDTO saveSource(SourceDTO sourceDTO) {
        Source save = sourceRepo.save(bourseMapper.fromSourceDTO(sourceDTO));
        return bourseMapper.fromSource(save);
    }

    @Override
    public SourceDTO updateSource(SourceDTO sourceDTO) throws SourceException {
        Source source = sourceRepo.findById(sourceDTO.getId()).orElse(null);
        if(source==null)
            throw new SourceException("Source not found");
        Source save = sourceRepo.save(bourseMapper.fromSourceDTO(sourceDTO));

        return bourseMapper.fromSource(save);
    }

    @Override
    public SourceDTO getSourceById(Long id) throws SourceException {
        Source source = sourceRepo.findById(id).orElseThrow(() -> {
            return new SourceException("source not found");
        });
        SourceDTO sourceDTO = bourseMapper.fromSource(source);
        return sourceDTO;
    }

    @Override
    public List<SourceDTO> getAllSource() {
        List<SourceDTO> collect = sourceRepo.findAll().stream().map(data -> {
            return bourseMapper.fromSource(data);
        }).collect(Collectors.toList());
        return collect;

    }

    @Override
    public void deleteSource(Long id) throws SourceException {
        sourceRepo.findById(id).orElseThrow(()->new SourceException("source nor found"));
        sourceRepo.deleteById(id);

    }

    @Override
    public Page<Source> getAllSourceByPage(int page, int size) {

        return sourceRepo.findAll(PageRequest.of(page, size));
    }

    DepenceRepo depenceRepo;
    @Override
//    public DepenceDTO saveDepence(DepenceDTO depenceDTO) {
//        //use depenceReq
//        //tester association Objet
//        //test of busniss rules (solde)
//        Depence save = depenceRepo.save(bourseMapper.fromDepenceDTO(depenceDTO));
//        return bourseMapper.fromDepence(save);
//    }
    public TestDepenceDTO saveDepence(DepenceReq req) throws BourseException, TypeDepenceException, DepenceException {
        // bourseReq
        // tester sur source
        //Bourse save = bourseRepo.save(bourseMapper.fromBourseDTO(bourseDTO));
        //return bourseMapper.fromBourse(save);
        System.out.println(req.toString());
//        Optional<Source> source = sourceRepo.findById(req.getSourceId());
        TestDepenceDTO testDepenceDTO= new TestDepenceDTO();
        Optional<Bourse> bourse = bourseRepo.findById(req.getBourseId());
        Optional<TypeDepence> typeDepence = typeDepenceRepo.findById(req.getTypeDepenceId());
        //if(sumBourse() > sumDepence()+req.getMontantDepence()) {


            if (bourse.isPresent() && typeDepence.isPresent() && sumBourse() >= sumDepence()+req.getMontantDepence()) {
                DepenceDTO depenceDTO = new DepenceDTO();
                depenceDTO.setDateDepence(req.getDateDepence());
                depenceDTO.setMontantDepence(req.getMontantDepence());
                depenceDTO.setRefDepence(req.getRefDepence());
                depenceDTO.setBenificiaire(req.getBenificiaire());
                depenceDTO.setBourse(bourseMapper.fromBourse(bourse.get()));
                depenceDTO.setTypeDepence(bourseMapper.fromTypeDepence(typeDepence.get()));
                Depence save = depenceRepo.save(bourseMapper.fromDepenceDTO(depenceDTO));
                testDepenceDTO.setDepenceDTO(depenceDTO);
                testDepenceDTO.setMoney(true);
                return testDepenceDTO;
            } else {
                testDepenceDTO.setMoney(false);
                return testDepenceDTO;

            }
        //}else {
        //    throw new BourseException("aaaa");
        //}


    }

//    @Override
//    public DepenceDTO saveDepencee(DepenceReq req) throws BourseException, TypeDepenceException, DepenceException {
//        System.out.println(req.toString());
////        Optional<Source> source = sourceRepo.findById(req.getSourceId());
//        Optional<Bourse> bourse = bourseRepo.findById(req.getBourseId());
//      System.out.println("la somme des bourse est " + bourseRepo.getSumBourse());
//        bourseRepo.getSumBourse();
//        if(bourseRepo.getSumBourse() < req.getMontantDepence()){
//
//            throw new DepenceException("le montant que vous voulez ajouter est indisponible dans la caisse");
//        }else {
//            if (bourse.isPresent() && typeDepence.isPresent()) {
//                DepenceDTO depenceDTO = new DepenceDTO();
//                depenceDTO.setDateDepence(req.getDateDepence());
//                depenceDTO.setMontantDepence(req.getMontantDepence());
//                depenceDTO.setRefDepence(req.getRefDepence());
//                depenceDTO.setBenificiaire(req.getBenificiaire());
//                depenceDTO.setBourse(bourseMapper.fromBourse(bourse.get()));
//                depenceDTO.setTypeDepence(bourseMapper.fromTypeDepence(typeDepence.get()));
//                Depence save = depenceRepo.save(bourseMapper.fromDepenceDTO(depenceDTO));
//                return bourseMapper.fromDepence(save);
//            } else {
//                throw new BourseException("Bourse not found or type of depense not found");
//            }
//        }
//    }

    @Override
    public TestDepenceDTO updateDepence(DepenceReq req) throws DepenceException, BourseException {

        Depence depence = depenceRepo.findById(req.getId()).orElse(null);
        if(depence==null){
            throw new DepenceException("depence not found");
        }else{
            TestDepenceDTO testDepenceDTO= new TestDepenceDTO();
            Optional<Bourse> bourse = bourseRepo.findById(req.getBourseId());
            Optional<TypeDepence> typeDepence = typeDepenceRepo.findById(req.getTypeDepenceId());
            //if(sumBourse() > sumDepence()+req.getMontantDepence()) {


            if (bourse.isPresent() && typeDepence.isPresent() && sumBourse() >= sumDepence()+req.getMontantDepence()) {
                DepenceDTO depenceDTO = new DepenceDTO();
                depenceDTO.setId(req.getId());
                depenceDTO.setDateDepence(req.getDateDepence());
                depenceDTO.setMontantDepence(req.getMontantDepence());
                depenceDTO.setRefDepence(req.getRefDepence());
                depenceDTO.setBenificiaire(req.getBenificiaire());
                depenceDTO.setBourse(bourseMapper.fromBourse(bourse.get()));
                depenceDTO.setTypeDepence(bourseMapper.fromTypeDepence(typeDepence.get()));
                Depence save = depenceRepo.save(bourseMapper.fromDepenceDTO(depenceDTO));
                testDepenceDTO.setDepenceDTO(depenceDTO);
                testDepenceDTO.setMoney(true);
                return testDepenceDTO;
            } else {
                testDepenceDTO.setMoney(false);
                return testDepenceDTO;

            }
        }

    }

    @Override
    public DepenceDTO getDepenceById(Long id) throws DepenceException {
        Depence depence = depenceRepo.findById(id).orElseThrow(() -> {
            return new DepenceException("depence not found");
        });
        DepenceDTO depenceDTO = bourseMapper.fromDepence(depence);
        return depenceDTO;
    }

    @Override
    public List<DepenceDTO> getAllDepence() {
        List<DepenceDTO> collect = depenceRepo.findAll().stream().map(data -> {
            return bourseMapper.fromDepence(data);
        }).collect(Collectors.toList());
        return collect;
    }





    @Override
    public void  deletDepence(Long id) throws DepenceException {
        try {
            depenceRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("depence not found");
        }
    }
    TypeDepenceRepo typeDepenceRepo;
    @Override
    public TypeDepenceDTO saveTypeDepence(TypeDepenceDTO typeDepenceDTO) {
        TypeDepence save = typeDepenceRepo.save(bourseMapper.fromTypeDepenceDTO(typeDepenceDTO));
        return bourseMapper.fromTypeDepence(save);
    }

    @Override
    public TypeDepenceDTO updateTypeDepence(TypeDepenceDTO typeDepenceDTO) throws TypeDepenceException {
        TypeDepence save = typeDepenceRepo.save(bourseMapper.fromTypeDepenceDTO(typeDepenceDTO));
        return bourseMapper.fromTypeDepence(save);
    }

    @Override
    public TypeDepenceDTO getTypeDepenceById(Long id) throws TypeDepenceException {
        TypeDepence typeDepence = typeDepenceRepo.findById(id).orElseThrow(() -> {
            return new TypeDepenceException("typeDepense not found");
        });
        TypeDepenceDTO typeDepenceDTO = bourseMapper.fromTypeDepence(typeDepence);
        return typeDepenceDTO;
    }

    @Override
    public List<TypeDepenceDTO> getAllTypeDepence() {
        List<TypeDepenceDTO> collect = typeDepenceRepo.findAll().stream().map(data -> {
            return bourseMapper.fromTypeDepence(data);
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deletTypeDepence(Long id) throws TypeDepenceException {
        try
        {
            typeDepenceRepo.deleteById(id);
        } catch (Exception e) {
            throw new TypeDepenceException("typedepence not found");
        }
    }

    @Override
    public Page<TypeDepence> getAllTypeByPage(int page, int size) {
        return typeDepenceRepo.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<DepenceDTO> findAllByBourse_Id(Long id) throws BourseException {
        bourseRepo.findById(id).orElseThrow(()-> new BourseException("Bourse not found"));
        List<DepenceDTO> collect = depenceRepo.findAllByBourse_Id(id).stream().map(data -> {
            return bourseMapper.fromDepence(data);
        }).collect(Collectors.toList());
        return collect;
    }




    @Override
    public List<DepenceDTO> findAllByTypeDepence_Id(Long id) throws TypeDepenceException{
        typeDepenceRepo.findById(id).orElseThrow(()->new TypeDepenceException("type depence not found"));
        List<DepenceDTO> collect = depenceRepo.findAllByTypeDepence_Id(id).stream().map(data -> {
            return bourseMapper.fromDepence(data);
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public double sumDepence() throws DepenceException {
        return depenceRepo.getSumDepense();
    }

    @Override
    public Page<Depence> getAllDepenceByPage(int page, int size) {
        return depenceRepo.findAll(PageRequest.of(page, size));
    }

    public double solde(){
        double solde = ((bourseRepo.getSumBourse())-(depenceRepo.getSumDepense()));
        return solde;
    }
}
