package MyUtil;

import java.util.List;

@FunctionalInterface
public interface IOnLoadData {
    void load(List<?> data);
}
